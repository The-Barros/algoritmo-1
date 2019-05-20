import java.io.*;
import java.net.*;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.TouchAdapter;
import lejos.robotics.ColorAdapter;
import lejos.hardware.sensor.EV3ColorSensor;

public class LegoEV3 {
	
	public static final int port = 1234;
		
	public static void main(String[] args) throws IOException, InterruptedException {
		
		EV3TouchSensor sensor = new EV3TouchSensor(SensorPort.S1);
		TouchAdapter touch = new TouchAdapter(sensor);
		boolean status = false; //false -> dormindo, true -> acordado
		int posicao = 0; //0 -> deitado, 1 -> sentado, 2 -> em pe
		EV3ColorSensor sensorCor = new EV3ColorSensor(SensorPort.S4);
		ColorAdapter cor = new ColorAdapter(sensorCor);
		//sensorCor.
		
		
		ServerSocket server = new ServerSocket(port);
		LCD.clear();
		LCD.drawString("Aguardando Cliente!", 0, 4);
		Socket client = server.accept();
		LCD.clear();
		LCD.drawString("Conectado!",0,4);
	
		InputStream in = client.getInputStream();
		DataInputStream dIn = new DataInputStream(in);
		OutputStream out = client.getOutputStream();
		DataOutputStream dOut = new DataOutputStream(out);
		
			
		LCD.clear();
		LCD.drawString("Rodando",0,4);
		LCD.clear();
		LCD.drawString("_+_  _+_", 0, 4);
		
		while(touch.isPressed() == false) {
			
			if(status == true && posicao == 0) {
				
				LCD.clear();
				LCD.drawString("Levantando", 4, 4);
				
				Motor.A.setSpeed(41);
				Motor.D.setSpeed(40);
				Motor.D.forward();
				Motor.A.forward();
				Thread.sleep(3500);
				Motor.A.stop();
				Motor.D.stop();
				Motor.A.close();
				Motor.D.close();
				
				LCD.clear();
				LCD.drawString("O O", 4, 4);
				
				Thread.sleep(2000);
				
				posicao = 2;
			}else if(status == false && posicao == 0) {
				LCD.clear();
				LCD.drawString("__  __", 0, 4);

				dOut.writeBoolean(status);
				dOut.flush();
				
				status = dIn.readBoolean();
			}else if(status == true && posicao == 2) {
				
				LCD.drawString("O O", 4, 4);
				
				
				
			}	
			
		}
		System.out.println(touch.isPressed());
		System.out.println(status);
		System.out.println(posicao);
		Thread.sleep(5000);
		
		client.close();
		System.exit(0);
	}
}
