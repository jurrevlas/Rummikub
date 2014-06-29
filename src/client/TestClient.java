package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import message.*;

import org.hamcrest.core.IsInstanceOf;

public class TestClient {
	public static void main(String[] args) throws IOException{
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		Socket socket = null;
		try {
			socket = new Socket("localhost",12345);
						
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(new Introduction("Player" + socket.getPort()));
			out.writeObject(new ChatMessage("Player" + socket.getPort(), "Hi girls, lets take a Selfy"));
		} catch (IOException e) {			
			//e.printStackTrace();
		}
		Object temp = new Object();
		
		
		while(true){
			
			try {
				if(in.available() <= 0)
					temp = in.readObject();
					if(temp instanceof ChatMessage)
						System.out.println(((ChatMessage) temp).getSender() + " " + ((ChatMessage) temp).getMessage());
					else System.out.println(temp);
			} catch (IOException | ClassNotFoundException e) {
				socket.close();
			}
			
		}
		
	}
}
