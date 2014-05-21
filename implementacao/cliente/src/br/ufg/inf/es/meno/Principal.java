package br.ufg.inf.es.meno;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gcm.GCMRegistrar;

public class Principal extends Activity {
	
	String TAG = "Principal::Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		
		// Register Device Button
				Button regbtn = (Button) findViewById(R.id.botaoObterRegID);

				regbtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Log.i(TAG, "Registering device");
						// Retrive the sender ID from GCMIntentService.java
						// Sender ID will be registered into GCMRegistrar
						GCMRegistrar.register(Principal.this,
								GCMIntentService.SENDER_ID);
					}
				});
	}
	
	/*public void obtemRegID(View v){
		Log.i("MeNo-Cliente", "Registrando o dispositivo...");
		GCMRegistrar.register(Principal.this, MeNoIntentService.SENDER_ID);
	}*/

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
