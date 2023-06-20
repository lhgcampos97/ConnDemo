package tcpdemo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) throws Exception {
		
		String clientIP = "127.0.0.1";
		int clientPort = 9000;
		
		// socket criado
		Socket s =  new Socket(clientIP, clientPort);
		
		// dados enviados ao servidor		
		OutputStream os = s.getOutputStream();
		DataOutputStream writer =  new DataOutputStream(os);
		
		// dados recebidos do servidor
		InputStreamReader is = new InputStreamReader(s.getInputStream());
		BufferedReader reader = new BufferedReader(is);
		
		// lê a entrada do usuário no terminal
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));				
		String texto = inFromUser.readLine();
		
		if (texto.equals("JOIN")) {
			
			String joinRequest = "JOIN " + clientIP + " " + clientPort;
	        writer.writeBytes(joinRequest + "\n");
			String joinResponse = reader.readLine();
			System.out.println("server> " + joinResponse);
			
	        while (true) {
	        	
	        	System.out.println("client> ");
	            texto = inFromUser.readLine();
	            writer.writeBytes(texto + "\n");
	
	            String response = reader.readLine();
	            System.out.println("server> " + response);
	
	            if (texto.equals("EXIT")) {
	                break; // Sai do loop quando digitar "EXIT"
	            }
	        }
		}
		// termina o socket
		s.close();
	}
}
