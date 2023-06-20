package tcpdemo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServerConcorrente {

	public static void main(String[] args) throws Exception {
		
		ArrayList<ThreadAtendimento> threadsClients = new ArrayList<>();
		
		// cria um novo socket
		ServerSocket serverSocket =  new ServerSocket(9000);
		
		while (true) {
			
			System.out.println("Esperando conexão");
			Socket no = serverSocket.accept(); // aceita uma conexão no socket criado
			ThreadAtendimento thread =  new ThreadAtendimento(no); // cria uma nova thread nesse socket
			threadsClients.add(thread); // Adiciona a thread à lista
			System.out.println("Conexão aceita");
			
			thread.start();
			
		}
	}

}
