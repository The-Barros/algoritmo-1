import java.io.*;
import java.net.*;

import lejos.hardware.lcd.LCD;

public class NewServer {
	
	public static final int port = 1234;
	
	public static void main(String [] args) throws IOException, InterruptedException {

		EV3TouchSensor sensor = new EV3TouchSensor(SensorPort.S4);
		TouchAdapter touch = new TouchAdapter(sensor);
		
		ServerSocket server = new ServerSocket(port);
		LCD.clear();
		LCD.drawString("Aguardando Cliente!", 0, 4);
		Socket client = server.accept(); // Espera chamada do cliente
		LCD.clear();
		LCD.drawString("Conectado!",0,4);	
	}
}
