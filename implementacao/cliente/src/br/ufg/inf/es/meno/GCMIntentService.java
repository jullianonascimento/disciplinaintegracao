package br.ufg.inf.es.meno;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	
	public static final String SENDER_ID = "170735191260"; 
	
	public GCMIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
		Log.i("MeNo-IntentService", "Registrado: regId=" + registrationId);
	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {
		Log.i("MeNo-IntentService", "Desregistrado: regId=" + registrationId);
	}

	@Override
	protected void onError(Context context, String errorId) {
		Log.e("MeNo-IntentService", "Erro: errorId=" + errorId);
	}

	@Override
	protected void onMessage(Context context, Intent data) {
		String message = data.getStringExtra("message");
		Intent intent = new Intent(this, TelaMensagem.class);
		intent.putExtra("message", message);

		// Inicia a activity no clique da notificação
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		// Cria a notificação
		Notification notification = new Notification.Builder(this)
				.setSmallIcon(R.drawable.meno_envelope)
				.setWhen(System.currentTimeMillis())
				.setContentTitle("MeNo - Mensagem Recebida")
				.setContentText(message).setContentIntent(pIntent)
				.getNotification();

		// Remove a notificação ao clicar
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.notify(R.string.app_name, notification);

		// Alerta o dispositivo quando a mensagem é recebida
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
		final PowerManager.WakeLock mWakelock = pm.newWakeLock(
				PowerManager.FULL_WAKE_LOCK
						| PowerManager.ACQUIRE_CAUSES_WAKEUP, "GCM_PUSH");
		mWakelock.acquire();

		// Tempo para o dispositivo entrar em suspensão
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				mWakelock.release();
			}
		};
		timer.schedule(task, 5000);

	}
}
