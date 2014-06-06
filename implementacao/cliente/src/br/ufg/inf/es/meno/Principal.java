package br.ufg.inf.es.meno;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gcm.GCMRegistrar;

public class Principal extends Activity {

	private final String TAG = "MeNo-Cliente";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);

	}

	public void registraDispositivo(View v) {
		Context context = Principal.this;

		if (!GCMRegistrar.isRegistered(context)) {
			Log.i(TAG, "Registrando o dispositivo...");
			GCMRegistrar.register(context, GCMIntentService.SENDER_ID);
		} else {
			String registrationId = GCMRegistrar.getRegistrationId(context);
			iniciaTelaStatusRegistro(registrationId);
		}

	}

	public void desregistraDispositivo(View v) {
		Context context = Principal.this;
		
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
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.cancelarRegistro) {
			desregistraDispositivo();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/

}
