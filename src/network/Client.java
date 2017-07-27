package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import model.BoardFileHandler;
import model.IModel;
import view.MainWindow;

public class Client implements Runnable {
	IModel model;
	String ipAddr;
	int port;
	DatagramSocket clientSocket = null;
	private MainWindow window;
	private boolean isClient;
	@Override
	public void run() {
		if(isClient=startClient()){
	        window.enableClientView();
			window.getRunKeyListener().setListening(true);
	        this.startClientLoop();
		}
	}

	public Client(MainWindow window,IModel model, String ip, int port) {
		this.window=window;
		this.model = model;
		this.ipAddr = ip;
		this.port = port;
	}


	public boolean startClient() { //Send Connection packet to host and setup window for Client mode.

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		try {
			clientSocket.setSoTimeout(60000);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		InetAddress IPAddress = null;
		try {
			IPAddress = InetAddress.getByName(ipAddr);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] sendData = new byte[4096];
		byte[] receiveData = new byte[4096];
		sendData[0] = 'C';
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		try {
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}

		DatagramPacket receivePacket;
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		try {
			clientSocket.receive(receivePacket);
			window.setStatusLabel("Connected to Host: "+ipAddr);
			window.getActionListener().enterClientMode();
			model.setClient(this);
			clientSocket.setSoTimeout(0);
			return true;
		} catch (Exception e) {
			if(e instanceof java.net.SocketTimeoutException){
				window.setStatusLabel("Error: Client has timed out trying to connect to host");
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return false;

	}
	public void startClientLoop(){ //Loop constantly waiting on the next board packet, and process accordingly
									//send keypresses on receipt or binary 1 if no keypresses present
		String loadedData;
		BoardFileHandler newHandler;
		DatagramPacket receivePacket;
		byte[] sendData = new byte[4096];
		byte[] receiveData = new byte[4096];
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
	
		while (isClient) {

			receiveData = new byte[4096];
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			try {
				clientSocket.receive(receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			loadedData = new String(receivePacket.getData());
			System.out.println(loadedData);
			newHandler = new BoardFileHandler(model);
			try {

				newHandler.loadFromString(loadedData);
				System.out.println(model.getGizmos().size());

				model.update();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (model.getKeysToSend().size() > 0) {
				while (model.getKeysToSend().size() > 0) {
					byte[] keyCode = model.getKeysToSend().remove().getBytes();
					DatagramPacket senderPacket = new DatagramPacket(keyCode, keyCode.length,
							receivePacket.getAddress(), receivePacket.getPort());
					try {
						System.out.println(keyCode);
						clientSocket.send(senderPacket);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Failed to send Packet");
					}
				}

			} else {
				byte[] test = { 1};
				DatagramPacket senderPacket = new DatagramPacket(test, test.length, receivePacket.getAddress(),
						receivePacket.getPort());
				try {
					clientSocket.send(senderPacket);
				} catch (IOException e) {
					System.out.println("Failed to send Packet");
				}
			}

		}
	}
	public void stopClient(){
		isClient=false;
		window.getRunKeyListener().setListening(false);
		model.setClient(null);
	}
}
