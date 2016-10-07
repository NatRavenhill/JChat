package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import chat.ChatServer;

/**
 * Class containing thread to process messages in the server
 * @author Natalie
 *
 */
public class ServerThread extends Thread {
	
	Socket clientSocket;
	String user;
	
	/**
	 * Constructor to initialise class
	 * 
	 * @param clientSocket
	 *            Socket of client to get message from
	 */
	public ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	
	public void run(){
		System.out.println("Started Server Thread");
		try {
			// create a BufferedReader to read from client
			BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			

			while (true) {
				// put input from Client in a string
				String message = br.readLine();	
				System.out.println("read client message");
				
				// if no message to send break (used to stop thread when
				// disconnecting Client)
				if (message == null)
					break;
				//if new user message, send message to all clients saying that they have joined.
				else if(message.startsWith("$U$")){
					ChatServer.send(message.substring(3) + " has joined the chat!");
				}
				else {
				ChatServer.send(message);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			// remove socket from server now the Client has been disconnected
			ChatServer.disconnect(clientSocket);
		}
	}

}
