package br.ufg.inf.es.meno;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Postagem {

	private static int TAMANHO_MAX_MENSAGEM = 2048; // 2 KB

	public static void enviaMensagem(String apiKey, Mensagem mensagem) {

		try {
			// URL do GCM para envio
			URL url = new URL("https://android.googleapis.com/gcm/send");

			// Configurando a conexão
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key=" + apiKey);
			conn.setDoOutput(true);

			// Convertendo a mensagem para JSON
			System.out.println("Convertendo a mensagem para JSON");
			ObjectMapper mapper = new ObjectMapper();
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			mapper.writeValue(wr, mensagem);
			//System.out.println("Tamanho do DataOutputStream em bytes: " + wr.size());
			System.out.println("Tamanho do texto em bytes: " + mensagem.getTamanhoDoTexto());

			if (mensagem.getTamanhoDoTexto() <= TAMANHO_MAX_MENSAGEM) {
				// Enviando a requisição
				System.out.println("Enviando a requisição 'POST' para: " + url);
				wr.flush();
				wr.close();

				// Obtendo a resposta
				int responseCode = conn.getResponseCode();
				System.out.println("Código da resposta: " + responseCode);

				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				System.out.println(response.toString());

			} else {
				System.out.println("A mensagem não pode ser enviada. Tamanho máximo excedido.");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
