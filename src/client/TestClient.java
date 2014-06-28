package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
	public static void main(String[] args){
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		try {
			Socket socket = new Socket("localhost",12345);
						
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());

		} catch (IOException e) {			
			e.printStackTrace();
		}
		Object temp = new Object();
		while(true){
			
			try {
				if(in.available() <= 0)
					System.out.println(in.readObject().toString());
				else
					System.out.print(".");
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
