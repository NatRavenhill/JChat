package main;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import chat.ChatClient;
import gui.GUI;

/**
 * Run this class to start a new client thread running and create a new chat client
 * @author Natalie
 *
 */
public class Start {

	public static void main(String args[]) throws IOException{
		   (new ChatClient()).start();
	    } 
}
