package br.ufg.inf.es.meno;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;

public class Principal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
	}

	public void registraDispositivo(View v) {
		Context context = Principal.this;
		Log.i("MeNo-Cliente", "Registrando o dispositivo...");
		GCMRegistrar.register(context, GCMIntentService.SENDER_ID);

		if (GCMRegistrar.isRegistered(context)) {
			String regID = GCMRegistrar.getRegistrationId(context);
			mostraRegID(regID);
			Toast.makeText(context,
					"Dê um clique longo sobre o Registro para selecioná-lo e copiá-lo",
					Toast.LENGTH_LONG).show();
		}
	}

	public void mostraRegID(String regID) {
		TextView textRegID = (TextView) findViewById(R.id.textRegID);
		textRegID.setText(regID);
	}

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
		}
		return super.onOptionsItemSelected(item);
	}

}
