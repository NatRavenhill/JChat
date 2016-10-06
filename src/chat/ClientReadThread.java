package chat;

import java.io.BufferedReader;
import java.io.IOException;

import gui.GUI;

public class ClientReadThread extends Thread{
	
	BufferedReader br;
	GUI gui;
	
	/**
	 * Constructor to initialise class
	 * 
	 * @param br
	 *            BufferedReader of Client to read messages from other clients
	 * @param gui
	 *            GUI that displays Client's chat
	 * @param playerType
	 *            type of player
	 */
	public ClientReadThread(BufferedReader br, GUI gui) {
		this.br = br;
		this.gui = gui;
	}
	
	/**
	 * Run method to run thread
	 */
	public void run() {
		try {
			while (true) {
				// set the value of current message string to what has been read
				// by the server
				String currentMessage = br.readLine();
				System.out.println("read message");
				
				// write this message in the text area of the client
				gui.textArea.append(currentMessage + "\n");
			}
			}
			catch(IOException e){
				e.printStackTrace();
			}
	}

}
