package br.ufg.inf.es.meno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Persistencia {
	
	private static String ARQUIVO = "RegIds.txt";

	public static String buscaRegIdPorNome(String nome) {

		String regId = "";
		boolean encontrou = false;

		try {
			File arquivo = new File(ARQUIVO);

			if (!arquivo.exists()) {
				System.out.println("Arquivo de Entrada não existe!");
				return null;
			}

			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);

			String linha;
			linha = br.readLine();
			while (linha != null && !encontrou) {
				String regIds[] = linha.split(";");
				if (regIds[0].equals(nome)) {
					regId = regIds[1];
					encontrou = true;
				}
				linha = br.readLine();
			}

			fr.close();
			br.close();

		} catch (FileNotFoundException ex) {
			System.err.println("Erro ao abrir o arquivo! Arquivo não existe.");
		} catch (IOException ex) {
			System.err.println("Erro ao acessar o arquivo!");
		}

		return regId;
	}

	public static List<String> buscaTodosRegIds() {
		
		List<String> conjuntoRegIds = new LinkedList<String>();

		try {
			File arquivo = new File(ARQUIVO);

			if (!arquivo.exists()) {
				System.out.println("Arquivo de Entrada não existe!");
				return null;
			}

			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);

			String linha;
			linha = br.readLine();
			while (linha != null) {
				String regIds[] = linha.split(";");

				conjuntoRegIds.add(regIds[1]);

				linha = br.readLine();
			}

			fr.close();
			br.close();

		} catch (FileNotFoundException ex) {
			System.err.println("Erro ao abrir o arquivo! Arquivo não existe.");
		} catch (IOException ex) {
			System.err.println("Erro ao acessar o arquivo!");
		}

		return conjuntoRegIds;
	}
	
	public static void listarAssinantes(){
		
		try {
			File arquivo = new File(ARQUIVO);

			if (!arquivo.exists()) {
				System.out.println("Arquivo de Entrada não existe!");
				return;
			}

			FileReader fr = new FileReader(arquivo);
			BufferedReader br = new BufferedReader(fr);

			String linha;
			linha = br.readLine();
			
			System.out.println("Assinantes disponíveis:");
			while (linha != null) {
				String nomes[] = linha.split(";");
				System.out.println(nomes[0]);
				
				linha = br.readLine();
			}

			fr.close();
			br.close();

		} catch (FileNotFoundException ex) {
			System.err.println("Erro ao abrir o arquivo! Arquivo não existe.");
		} catch (IOException ex) {
			System.err.println("Erro ao acessar o arquivo!");
		}

	}

}
