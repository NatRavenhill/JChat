package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import chat.ChatClient;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Class containing GUI for chat client
 * @author Natalie
 *
 */
public class GUI {

	public JFrame frmJchat;
	private JTextField textField;
	private static String username = "";
	public static JTextArea textArea;
	private JScrollPane scrollPane;
	
	//print writer for writing messages to the server
	PrintWriter pw;
	
	//contains message in textfield
	private String message = "";
	
	//contents of whole text pane
	private String text = "";
	


	/**
	 * Launch the application.
	 * @throws IOException 
	 */

	/**
	 * Create the application.
	 */
	public GUI(String username, PrintWriter pw) {
		this.username = username;
		this.pw = pw;
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void createAndShowGUI() {
		frmJchat = new JFrame();
		frmJchat.setTitle("JChat");
		frmJchat.setBounds(100, 100, 450, 300);
		frmJchat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJchat.getContentPane().setLayout(null);
		
		// WindowListener to close connection when User closes chat window
		frmJchat.addWindowListener(new WindowAdapter() {
			/**
			 * Stop running threads when the user closes the chat client GUI
			 */
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				// send a message to all connected users to say this Client is
				// disconnecting
				pw.println(username + " is disconnecting...");

				// flush PrintWriter so any other messages can be sent
				pw.flush();

				// Close this PrintWriter
				pw.close();
			}
		});

		
		textField = new JTextField();
		
		//send text to server on pressing enter
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pw.println(username + ": " + textField.getText());
				pw.flush();
				textField.setText("");
			}
		});
		textField.setBounds(224, 235, 200, 20);
		frmJchat.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUser = new JLabel(username + " is logged in.");
		lblUser.setBounds(10, 16, 143, 14);
		frmJchat.getContentPane().add(lblUser);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 11, 200, 213);
		frmJchat.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
	
		frmJchat.setVisible(true);
	
	}
}
