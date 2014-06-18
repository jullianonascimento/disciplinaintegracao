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
	
}
