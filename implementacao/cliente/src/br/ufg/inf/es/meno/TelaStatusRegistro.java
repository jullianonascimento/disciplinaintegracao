package br.ufg.inf.es.meno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TelaStatusRegistro extends Activity {
	
	private final String TAG = "Status-Registro";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_status_registro);
		
		recebeStatus();
	}
	
	public void recebeStatus(){
		Intent intent = getIntent();
		String status = intent.getStringExtra("status");
		TextView textViewStatus = (TextView) findViewById(R.id.textViewExibeStatus);
		textViewStatus.setText(status);
		Log.i(TAG, status);
	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_exibe_registro, menu);
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
	}*/



}
