package br.ufg.inf.es.meno;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gcm.GCMRegistrar;

public class MeNoClientePrincipal extends Activity {

	private final String TAG = "MeNo-Cliente";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);

	}

	public void registraDispositivo(View v) {
		Context context = MeNoClientePrincipal.this;

		if (!GCMRegistrar.isRegistered(context)) {
			Log.i(TAG, "Registrando o dispositivo...");
			GCMRegistrar.register(context, GCMIntentService.SENDER_ID);
		} else {
			String registrationId = GCMRegistrar.getRegistrationId(context);
			iniciaTelaStatusRegistro(registrationId);
		}

	}

	public void desregistraDispositivo(View v) {
		Context context = MeNoClientePrincipal.this;
		
		if (GCMRegistrar.isRegistered(context)) {
			Log.i(TAG, "Desregistrando o dispositivo...");
			GCMRegistrar.unregister(context);
		} else {
			Log.i(TAG, "O dispositivo não é registrado.");
			iniciaTelaStatusRegistro("O dispositivo não é registrado.");
		}

	}
	
	public void iniciaTelaStatusRegistro(String status){
		Intent intent = new Intent(this, TelaStatusRegistro.class);
		intent.putExtra("status", status);
		startActivity(intent);
	}

}
