package br.ufg.inf.es.meno;

import java.util.List;
import java.util.Scanner;

public class Principal {

	public static String APIKEY = "AIzaSyDCLhEymszT6nCTjmbgFNrSyWkkDqTGmZY";

	public static void main(String[] args) {

		menu();

	}

	public static void menu() {

		Scanner leNumero = new Scanner(System.in);
		int opcao = 0;

		System.out
				.println("---- Bem vindo ao MeNo ---- Aplicação para envio de mensagens ----");

		do {
			System.out.println("\n1. Enviar mensagem para um assinante");
			System.out.println("2. Enviar mensagem para todos os assinantes");
			System.out.println("0. Sair");
			System.out.println("Digite a opção desejada:");
			opcao = leNumero.nextInt();

			switch (opcao) {
			case 1: {
				menuEnviaMensagem();
				break;
			}
			case 2: {
				menuEnviaParaTodos();
				break;
			}
			case 0: {
				System.out.println("Finalizando aplicação.");
				break;
			}
			default: {
				System.out.println("Opção inválida.");
			}
			}

		} while (opcao != 0);

	}

	private static void menuEnviaParaTodos() {
		Scanner leString = new Scanner(System.in);

		System.out.println("Digite uma mensagem: ");
		String textoMensagem = leString.nextLine();

		Mensagem mensagem = new Mensagem("Titulo Padrao", textoMensagem);

		List<String> conjuntoRegIds = Persistencia.buscaTodosRegIds();

		for (String novoRegId : conjuntoRegIds) {
			mensagem.addRegId(novoRegId);
		}

		System.out.println("Enviando Mensagem .....");
		Postagem.enviaMensagem(APIKEY, mensagem);

	}

	private static void menuEnviaMensagem() {
		Scanner leString = new Scanner(System.in);

		System.out.println("Digite o nome do assinante: ");
		String nomeAssinante = leString.nextLine();

		String regId = Persistencia.buscaRegIdPorNome(nomeAssinante);
		if (regId.equals("")) {
			System.out.println("O registro não foi encontrado.");
		} else {
			System.out.println("Digite uma mensagem: ");
			String textoMensagem = leString.nextLine();

			Mensagem mensagem = new Mensagem(regId, "Titulo Padrao",
					textoMensagem);

			System.out.println("Enviando Mensagem .....");
			Postagem.enviaMensagem(APIKEY, mensagem);
		}

	}
}
