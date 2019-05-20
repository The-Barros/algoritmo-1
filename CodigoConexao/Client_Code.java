import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Client_Code {
	
	public static void main(String[] args) throws IOException {
		
		String ip = "10.0.1.1";
		if(args.length > 0)
			ip = args[0];
		
		Socket sock = new Socket(ip, 1234);
		System.out.println("Conectado!");
		InputStream in = sock.getInputStream();
		DataInputStream dIn = new DataInputStream(in);
		String str = dIn.readUTF();
		JOptionPane.showMessageDialog(null,str + " Volts", "INFORMAÇÃO", 
				JOptionPane.WARNING_MESSAGE);
	
		sock.close();
		
		
	}

}
