package app;

import java.net.*;
import java.io.*;
import java.util.*;

public class Chat {
    private static final String TERMINATE = "Exit";
    static String name;
    static volatile boolean finished = false;
    public ArrayList<String> receivedMessages  = new ArrayList<String>();
    MulticastSocket socket;
    InetAddress group;
    int port;
    
    public Chat(){
    	System.out.println("CHAT IS WORKING.");
    	
    	try {
            group = InetAddress.getByName("239.0.0.0");
            port = Integer.parseInt("1234");
            
            File nameFile = new File("src/app/name.txt");
            
            if (nameFile.length() != 0 && nameFile.exists()) {
            	Scanner reader = new Scanner(nameFile);
            	
            	name = reader.nextLine();
            	reader.close();
            }
            
            socket = new MulticastSocket(port);

            // Since we are deploying
            socket.setTimeToLive(0);
            
            //this on localhost only (For a subnet set it as 1)  
            socket.joinGroup(group);
            
            Thread t = new Thread(new ReadThread(socket, group, port, this));
          
            // Spawn a thread for reading messages
            t.start(); 
            
            String start = name + " joined the chat.";
            System.out.println(start);
            
            byte[] buffer = start.getBytes();
            DatagramPacket datagram = new DatagramPacket(buffer,buffer.length,group,port);
            socket.send(datagram);
            
            // sent to the current group
            System.out.println("Socket successfully created!");

        }
        catch(SocketException se)
        {
            System.out.println("Error creating socket");
            se.printStackTrace();
        }
        catch(IOException ie)
        {
            System.out.println("Error reading/writing from/to socket");
            ie.printStackTrace();
        }
    }
    
    public void sendMessage(String message) throws IOException {
    	System.out.println("CHAT SENT: " + message);
    	
            if(message.equalsIgnoreCase(Chat.TERMINATE)){
                finished = true;
                socket.leaveGroup(group);
                message = name + " has left the game.";
                byte[] buffer = message.getBytes();
                DatagramPacket datagram = new DatagramPacket(buffer,buffer.length,group,port);
                socket.send(datagram);
                socket.close();
                System.exit(0);
            }
            
            message = name + ": " + message;
            byte[] buffer = message.getBytes();
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
            socket.send(datagram);
    }
    
    public void addReceivedMessage(String received) {
    	System.out.println("MESSAGE ADDED TO ARRAY: " + received);
    	receivedMessages.add(received);
    }
    
    public ArrayList<String> getReceivedMessages(){
    	return receivedMessages;
    }
    
    public int getArraySize() {
    	return receivedMessages.size();
    }
}

class ReadThread implements Runnable {
    private MulticastSocket socket;
    private InetAddress group;
    private int port;
    private Chat chat;
    
    private static final int MAX_LEN = 1000;
    ReadThread(MulticastSocket socket, InetAddress group, int port, Chat chat) {
        this.socket = socket;
        this.group = group;
        this.port = port;
        this.chat = chat;
    }
      
    @Override
    public void run() {
        while(!Chat.finished) {
                byte[] buffer = new byte[ReadThread.MAX_LEN];
                DatagramPacket datagram = new
                DatagramPacket(buffer,buffer.length,group,port);
                String message;
            try {
                socket.receive(datagram);
                message = new String(buffer,0,datagram.getLength(),"UTF-8");
                if(message != null && !message.startsWith(Chat.name))
                    System.out.println("CHAT RECEEEEEEEEEIVED: " + message);
                	chat.addReceivedMessage(message);
            }
            catch(IOException e)
            {
                System.out.println("Socket closed!");
            }
        }
    }
}
