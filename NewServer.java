import java.io.*;
import java.net.*;

import lejos.hardware.Battery;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.TouchAdapter;

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
		
		OutputStream out = client.getOutputStream(); 
		DataOutputStream dOut = new DataOutputStream(out);
		dOut.writeUTF("Conectado!");			
		dOut.flush();
		Thread.sleep(1000);
		
		InputStream in = client.getInputStream();
		DataInputStream dIn = new DataInputStream(in);
		Boolean sms = dIn.readBoolean();
		if(sms = true) {
			
			LCD.clear();
			LCD.drawString("AddBelief",0,4);
			Thread.sleep(1000);
			LCD.clear();
			LCD.drawString("Esperando Toque...",0,4);
			
			
		}else {
			LCD.clear();
			LCD.drawString("NoAddBelief",0,4);
		}
		
		while(touch.isPressed() == false) {
			
		}

		dOut.writeBoolean(touch.isPressed());
		dOut.flush();
		
		LCD.clear();
		LCD.drawString("Touch OK",0,4);
		
		Thread.sleep(10000);
		
		LCD.clear();
		LCD.drawString("Verificando bateria",0,4);
		//while(touch.isPressed() == false) {}
		dOut.writeFloat(Battery.getVoltage());
		dOut.flush();
		Thread.sleep(2000);
		
		while(touch.isPressed() == false) {
			
			
			
		}
		
	}
}
