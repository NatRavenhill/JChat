package chat;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Class to start up the Server. 
 * @author Natalie
 *
 */
public class ChatServer {

	// List to store PrintWriters of clients
		static ArrayList<PrintWriter> printwriterList;
		// List to store messages sent by all clients
		static ArrayList<String> messageList;
		//Server thread to do processing
		private static ServerThread thread;
	
	
	public static void main(String[] args) throws IOException {
		
		// Create ArrayList to store output streams of the clients
		printwriterList = new ArrayList<PrintWriter>();

		// Create ArrayList to store messages being sent
		messageList = new ArrayList<String>();
		
		//Open the Server Socket
		Integer portnum = 1090;
		ServerSocket server = new ServerSocket(portnum);
		
		try {
			while(true){
				//wait for client request
				Socket client = server.accept();
					// Create a PrintWriter to WRITE TO CLIENT SOCKET
					PrintWriter pw = new PrintWriter(client.getOutputStream());

					// Add this to the list of PrintWriters
					printwriterList.add(pw);

				//create i/0 streams for communicating to the client
				DataOutputStream os = new DataOutputStream(client.getOutputStream());
				
				System.out.println("client connected to server");
				//communicate with client
				
				// create ServerThread to do functionality
				thread = new ServerThread(client);
				thread.start();
			}
		}
		catch (IOException e) {
			System.out.println("Can't connect to server");
		}
		finally {
			server.close();
		}
		
	}
	
	/**
	 * Send method sends messages sent to the server to all the clients
	 * connected to it.
	 * 
	 * @param message
	 *            message being sent to clients
	 * @throws IOException
	 *             exception called if no message to be sent
	 */
	public static void send(String message) throws IOException {
		// add current message to list of messages
		messageList.add(message);
		
		System.out.println(message);

		// write message to the output of each client
		for (int i = 0; i < printwriterList.size(); i++) {
			printwriterList.get(i).println(message);
			printwriterList.get(i).flush();
		}

	}
	
	/**
	 * Method to remove a Client from the Server
	 * 
	 * @param clientSocket
	 *            Socket of client to be removed
	 */
	public static void disconnect(Socket clientSocket) {

		try {
			// close Socket of Client
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Couldn't remove client");
		} finally {
			// Remove this socket from list of sockets
			printwriterList.remove(clientSocket);
		}

	}
}
