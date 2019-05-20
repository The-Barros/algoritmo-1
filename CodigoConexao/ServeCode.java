import java.io.*;
import java.net.*;

public class NewServer {
	
	public static final int port = 1234;
	
	public static void main(String [] args) throws IOException, InterruptedException {
	
		ServerSocket server = new ServerSocket(port);
		Socket client = server.accept(); // Espera chamada do cliente
		Syste.out.println("Conectado!");
	}
}
