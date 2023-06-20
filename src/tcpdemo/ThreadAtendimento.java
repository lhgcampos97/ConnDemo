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
			
			while (true) {
	            String texto = reader.readLine(); // armazena a entrada em uma variável
	            
	            if (texto.equals("EXIT")) {
	                break; // Sai do loop quando recebe "EXIT"
	            }
	            
	            writer.writeBytes(texto.toUpperCase() + "\n");
	        }
	        
	        no.close();
			
		} catch (Exception e) {
			
		}
		
	}

}
