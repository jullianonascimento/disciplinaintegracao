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
	private final String TAG = "IntentService";
	private static int TAMANHO_MAX_MENSAGEM = 2048; // 2 KB
	
	public GCMIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
		Log.i(TAG, "Registrado: regId=" + registrationId);
		Intent intent = new Intent(this, TelaStatusRegistro.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("status", registrationId);
		startActivity(intent);
	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {
		Log.i(TAG, "Desregistrado: regId=" + registrationId);
		Intent intent = new Intent(this, TelaStatusRegistro.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("status", "O registro foi cancelado com sucesso.");
		startActivity(intent);
	}

	@Override
	protected void onError(Context context, String errorId) {
		Log.e(TAG, "Erro: errorId=" + errorId);
		Intent intent = new Intent(this, TelaStatusRegistro.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("status", "Aconteceu um erro.");
		startActivity(intent);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onMessage(Context context, Intent data) {
		
		String title = data.getStringExtra("title");
		String message = data.getStringExtra("message");
		
		int tamanhoTitle = title.getBytes().length;
		int tamanhoTexto = message.getBytes().length;
		int tamanhoMensagem = tamanhoTitle + tamanhoTexto;
		
		Log.i(TAG, "Tamanho da mensagem em bytes: " + tamanhoMensagem);
		
		if (tamanhoMensagem <= TAMANHO_MAX_MENSAGEM){
				
			Intent intent = new Intent(this, TelaMensagem.class);
			intent.putExtra("title", title);
			intent.putExtra("message", message);
	
			// Inicia a activity no clique da notifica��o
			PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
					PendingIntent.FLAG_UPDATE_CURRENT);
	
			// Cria a notifica��o
			Notification notification = new Notification.Builder(this)
					.setSmallIcon(R.drawable.meno_envelope)
					.setWhen(System.currentTimeMillis())
					.setContentTitle("Mensagem Recebida")
					.setContentText(title).setContentIntent(pIntent)
					.getNotification();
	
			// Remove a notifica��o ao clicar
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
	
			NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			manager.notify(R.string.app_name, notification);
	
			// Alerta o dispositivo quando a mensagem � recebida
			PowerManager pm = (PowerManager) context
					.getSystemService(Context.POWER_SERVICE);
			final PowerManager.WakeLock mWakelock = pm.newWakeLock(
					PowerManager.FULL_WAKE_LOCK
							| PowerManager.ACQUIRE_CAUSES_WAKEUP, "GCM_PUSH");
			mWakelock.acquire();
	
			// Tempo para o dispositivo entrar em suspens�o
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					mWakelock.release();
				}
			};
			timer.schedule(task, 5000);
			
		} else{
			Log.i(TAG, "A mensagem n�o pode ser recebida. Tamanho m�ximo excedido.");
		}

	}
}
