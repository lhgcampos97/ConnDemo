package tcpdemo;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerConcorrente {
	
	public static void main(String[] args) throws Exception {
		
		ServerSocket serverSocket =  new ServerSocket(9000);
		
		while (true) {
			
			System.out.println("Esperando conn");
			Socket no = serverSocket.accept();
			System.out.println("Conn aceita");
			
			ThreadAtendimento thread =  new ThreadAtendimento(no);
			thread.start();
			
		}
	}

}
