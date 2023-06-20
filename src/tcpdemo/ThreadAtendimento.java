package tcpdemo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ThreadAtendimento extends Thread {

	private Socket no = null; // inicializa um socket

	public ThreadAtendimento(Socket node) {
		no = node; // recebe o socket como criado pelo servidor como argumento
	}

	public void run() {
		try {
			// cria as variáveis que recebem a entrada
			InputStreamReader is = new InputStreamReader(no.getInputStream());
			BufferedReader reader = new BufferedReader(is);

			//cria as variáveis que recebem a saída
			OutputStream os = no.getOutputStream();
			DataOutputStream writer = new DataOutputStream(os);


			String texto = reader.readLine(); // armazena a entrada em uma variável

			if (texto.startsWith("JOIN")) {
				// Extrai o IP e a porta do cliente
				String[] tokens = texto.split(" ");
				String clientIP = tokens[1];
				int clientPort = Integer.parseInt(tokens[2]);
	            System.out.println("Peer " + clientIP + " " + clientPort + " quer ser adicionado à rede");
	            System.out.println("Peer adicionado");
				// Armazena as informações do cliente para futuras consultas
				// Envia a resposta JOIN_OK para o cliente
				writer.writeBytes("JOIN_OK\n");
				while (true) {
					texto = reader.readLine();
					writer.writeBytes(texto.toUpperCase() + "\n");
				}
			} 

			no.close();

		} catch (Exception e) {

		}

	}

}
