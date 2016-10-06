package chat;

import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import gui.GUI;

public class ChatClient extends Thread {
	
	public static String answer;
	PrintWriter pw;
	String username;
	GUI gui;
	
	public ChatClient() throws IOException{
		
		//open the socket
		
		
		
		
		//read and write to stream
		//String answer = input.readLine();
		//System.out.println(answer);
		
		
		//close the streams
		//input.close();
		
		//close the socket
		//s.close();
		//System.exit(0);
	}
	
	public void setup() throws UnknownHostException{
		
		
		// Create InetAdress to store location of server
				// (CHANGE THIS TO LOCATION OF SERVER MACHINE WHEN NETWORKING ON
				// MULTIPLE MACHINES)
		InetAddress localhost = InetAddress.getLocalHost();
		////String localhost = "192.168.1.103";
		Integer portnum = 1090;
		
		//create socket with host
		try{
		Socket socket = new Socket(localhost,portnum);
		// Get OUTPUTStream and store in a PrintWriter to WRITE TO SERVER
	    pw = new PrintWriter(socket.getOutputStream());
	    
	    	//get username
	  		String username = JOptionPane.showInputDialog("Enter your name:");
	  		
	  		//create and show GUI
	  		gui = new GUI(username, pw);
	  		gui.createAndShowGUI();
	  		
	  		pw.println("$U$" + username);
	  		pw.flush();
	  		
		
		//open an input stream and output stream
	    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    
	    // Create a thread to receive messages
	 	ClientReadThread read = new ClientReadThread(input, gui);

	 	// Start thread for receiving messages
	 	read.start();
	    
	    //start close method
		}
		catch(ConnectException e){
			//Print an error message if the server is not currently running or client can't find server
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
				    "Server not found, please try again....",
				    null,
				    JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	    catch (IOException e) {
			e.printStackTrace();
		}
		
	    
	}
	
	//called when thread ran
	public void run(){
		try {
			this.setup();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
