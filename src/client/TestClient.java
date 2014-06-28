package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
	public static void main(String[] args){
		try {
			Socket socket = new Socket("localhost",12345);
			
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		Object temp = new Object();
		while(true){
			
		}
		
	}
}
