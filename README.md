# JChat

The repository contains a Chat Client that was originally included in the Game I contributed to in my second year Team Project at university.  

![](https://github.com/NatRavenhill/JChatSoftwareSystemsComponents/blob/master/chat.PNG)

It uses a Client-Server architecture. The Server is ran from the ChatServer class and contains a thread called ServerThread which processes incoming connections from clients and sends messages to them all using an ArrayList of different PrintWriters for each client.  

The Clients are ran by running the Start class. This class asks the user their name, then runs a ChatClient thread which opens a socket, connecting the user to the chat and starts the GUI. In the GUI the user can see all the other users' messages since they connected and be informed of other users connecting and disconnecting.  

To send a message, they type in the lower text field and press enter.  
