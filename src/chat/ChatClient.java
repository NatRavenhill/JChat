package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import gui.GUI;

/**
 * Class containing a thread that opens a socket for a client on the network to start the chat
 * @author Natalie
 *
 */
public class ChatClient extends Thread {
	
	//PrintWriter for writing to socket
	PrintWriter pw;
	//String to hold current user's username
	String username;
	//GUI for chat client
	GUI gui;
	
	
	/**
	 * sets up the Socket to read messages 
	 * @throws UnknownHostException
	 */
	public void setup() throws UnknownHostException{
		
		// Create InetAdress to store location of server
		// (CHANGE THIS TO LOCATION OF SERVER MACHINE WHEN NETWORKING ON
		// MULTIPLE MACHINES)
		InetAddress localhost = InetAddress.getLocalHost();
		////String localhost = "192.168.1.103";
		Integer portnum = 1090;
		
		//create socket with host
		try {
			Socket socket = new Socket(localhost,portnum);
			// Get OUTPUTStream and store in a PrintWriter to WRITE TO SERVER
			pw = new PrintWriter(socket.getOutputStream());
	    
	    	//get username
	  		String username = JOptionPane.showInputDialog("Enter your name:");
	  		
	  		//create and show GUI
	  		gui = new GUI(username, pw);
	  		gui.createAndShowGUI();
	  		
	  		//Send a special message with the username to the server
	  		pw.println("$U$" + username);
	  		//clear the print writer
	  		pw.flush();
		
	  		//open an input stream and output stream
	  		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    
	  		// Create a thread to receive messages
	  		ClientReadThread read = new ClientReadThread(input, gui);

	  		// Start thread for receiving messages
	  		read.start();
		}
		catch(ConnectException e){
			//Print an error message if the server is not currently running or client can't find server
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Server not found, please try again....", null,JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	    catch (IOException e) {
			e.printStackTrace();
		}
		
	    
	}
	
	/**
	 * Called when thread started
	 */
	public void run(){
		try {
			this.setup();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
