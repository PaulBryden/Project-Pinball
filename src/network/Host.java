package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import model.BoardFileHandler;
import model.IModel;
import view.MainWindow;

public class Host implements Runnable{
	int hostPort;
	BoardFileHandler fileHandler;
	private DatagramSocket serverSocket;
    byte[] receiveData = new byte[4096];
    byte[] sendData = new byte[4096];
    IModel gameModel;
    MainWindow window;
    InetAddress returnAddr;
    HashMap<InetAddress,Integer> listOfClients;
    
	public Host(MainWindow window,BoardFileHandler boardFileHandler, IModel gameModel, int port) {
		this.window =window; //Setup window reference for status messages
		listOfClients=new HashMap<>(); //Setup hashmap of all clients and ports
		this.fileHandler=boardFileHandler; //serializer reference
		hostPort=port; //Keep track of host port
		this.gameModel=gameModel;
	}
	
	public int startHost(){ //Initialize socket, await connection from client, add client and switch to host
							//on successful connection
		try {
			serverSocket= new DatagramSocket(hostPort);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			window.setStatusLabel("Error: Failed to bind to port");
			return -1;
		}
		try {
			serverSocket.setSoTimeout(60000);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean Response=false;
		while(!Response){
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
			serverSocket.receive(receivePacket);
			Response=true;
		} catch (Exception e) {
			if(e instanceof java.net.SocketTimeoutException){
				window.setStatusLabel("Error: Host has timed out waiting on a connection.");
				disconnect();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
        System.out.println("RECEIVED:");
        System.out.println(receivePacket.getAddress());
        System.out.println(receivePacket.getPort());

	
        listOfClients.put(receivePacket.getAddress(), receivePacket.getPort());
        DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                try {
					serverSocket.send(sendPacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Failed to send Packet");
				} 
		}
		try {
			serverSocket.setSoTimeout(5000);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        window.setStatusLabel("Host: Connected to client");
        gameModel.setHost(this);
		return 1;
	}
	
	public void sendBoard(){ //serialize and send the current board to all clients.
        try {
        	sendData=new byte[4096];
			sendData = fileHandler.saveToString().getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Iterator it = listOfClients.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, (InetAddress)pair.getKey(), (Integer)pair.getValue());
                    try {
						serverSocket.send(sendPacket);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Failed to send Packet");
					} 
        }
	      
	      
        
	}
	
	public boolean receiveKeys(){ //wait on board receipt or keypress
		receiveData= new byte[4096];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	      try {
	    
			serverSocket.receive(receivePacket);
			String type;
	        String loadedData = new String(receivePacket.getData());
	        Scanner scan = new Scanner(loadedData);
	        String nextString=scan.next();
	        System.out.println(nextString);
	        if(nextString.contains("Pressed")){
	     	  gameModel.processKeyPressedTrigger(scan.nextInt()); //Process keypress receipt
	        }else if((nextString.contains("Released"))){
	    	  gameModel.processKeyReleasedTrigger(scan.nextInt()); //Process keyrelease receipt
	        }
            System.out.println(loadedData);
		    } catch (Exception e) {
			if(e instanceof java.net.SocketTimeoutException){
				window.setStatusLabel("Error: Client has timed out.");
				disconnect();
				return false;
			}
			e.printStackTrace();
		}
	      return true;
	}
	public void disconnect(){
		serverSocket.disconnect();
		serverSocket.close();
        gameModel.setHost(null);
        window.build();

	}

	@Override
	public void run() {
		startHost();
	}

}
