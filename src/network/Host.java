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

public class Host {
	int hostPort;
	BoardFileHandler fileHandler;
	private DatagramSocket serverSocket;
    byte[] receiveData = new byte[4096];
    byte[] sendData = new byte[4096];
    IModel gameModel;

    InetAddress returnAddr;
    HashMap<InetAddress,Integer> listOfClients;
    
	public Host(BoardFileHandler boardFileHandler, IModel gameModel, int port){

		listOfClients=new HashMap<>();
		this.fileHandler=boardFileHandler;
		hostPort=port;
		this.gameModel=gameModel;
	}
	
	public int startHost(){
		try {
			serverSocket= new DatagramSocket(hostPort);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to bind Port");
			return -1;
		}
		boolean Response=false;
		while(!Response){
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
			serverSocket.receive(receivePacket);
			Response=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
        System.out.println("RECEIVED:");
        System.out.println(receivePacket.getAddress());
        System.out.println(receivePacket.getPort());

	
        listOfClients.put(receivePacket.getAddress(), receivePacket.getPort());
		}
		return 1;
	}
	
	public void sendBoard(){
        try {

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
	
	public void receiveKeys(){
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	      try {
	    
			serverSocket.receive(receivePacket);
			String type;
	           String loadedData = new String(receivePacket.getData());

			      Scanner scan = new Scanner(loadedData);
			      String nextString=scan.next();
			      System.out.println(nextString);
			      if(nextString.contains("Pressed")){
			    	  gameModel.processKeyPressedTrigger(scan.nextInt());
			      }else if((nextString.contains("Released"))){
			    	  gameModel.processKeyReleasedTrigger(scan.nextInt());
			      }
	          System.out.println(loadedData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
