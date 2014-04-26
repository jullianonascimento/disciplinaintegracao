package br.ufg.inf.es.meno;

import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) {
		
		String apiKey = "AIzaSyDCLhEymszT6nCTjmbgFNrSyWkkDqTGmZY";
		String regId = "APA91bGbCuzqLt3Csz6ngG49UQ3DEq1m44R8BZQQO6HbxcSOXHX0Kv-KarfoK-pSY4PTaxSmH5x8CyYl6AveTNwIi-rWiP6wL8R29tTs42CAHN_QbW6wb8lF7PDai-uuvLrvNqRYij52Au-kjp7NaIlazGlq4lNn2uhiFHg2UmKb9EEEkIekQ2Q";
		
		Scanner leString = new Scanner(System.in);
		System.out.println("---- Bem vindo ao MeNo ---- Aplicação para envio de mensagens ----");
		System.out.println("Digite uma mensagem: ");
		String textoMensagem =  leString.nextLine();
		
		Mensagem mensagem = criaMensagem(regId, "Titulo Padrao", textoMensagem);
		//Mensagem mensagem = new Mensagem(regId, "Titulo Padrao", textoMensagem);
		
		System.out.println("Enviando Mensagem .....");
		Postagem.enviaMensagem(apiKey, mensagem);

	}
	
	public static Mensagem criaMensagem(String regId, String title, String message){
		Mensagem msg = new Mensagem();
		msg.addRegId(regId);
		msg.createData(title, message);
		return msg;
	}
}
