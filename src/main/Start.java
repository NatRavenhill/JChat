package main;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import chat.ChatClient;
import gui.GUI;

public class Start {

	public static void main(String args[]) throws IOException{
		  // String username = JOptionPane.showInputDialog("Enter message:");
		   //SwingUtilities.invokeLater(new Runnable() {
	         //    public void run() {
	           //       GUI gui = new GUI(username);
	             //     gui.frmJchat.setVisible(true);
	            // }

	        //});
		   (new ChatClient()).start();
	    } 
}
