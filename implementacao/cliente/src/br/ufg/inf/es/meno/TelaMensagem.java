package br.ufg.inf.es.meno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TelaMensagem extends Activity {
	
	String titulo;
	String mensagem;
	TextView textViewDoTitulo;
	TextView textViewDaMensagem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_mensagem);

		Intent i = getIntent();
		titulo = i.getStringExtra("title");
		mensagem = i.getStringExtra("message");
		
		textViewDoTitulo = (TextView) findViewById(R.id.exibeTitulo);
		textViewDaMensagem = (TextView) findViewById(R.id.exibeMensagem);
		
		textViewDoTitulo.setText(titulo);
		textViewDaMensagem.setText(mensagem);
	}

}
