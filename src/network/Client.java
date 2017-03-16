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

public class Client implements Runnable {
	IModel model;
	boolean isClient = false;
	String ipAddr;
	int port;
	DatagramSocket clientSocket = null;
	@Override
	public void run() {
		this.startClientLoop();
	}

	public Client(IModel model, String ip, int port) {
		this.model = model;
		this.ipAddr = ip;
		this.port = port;
	}

	public boolean isClient() {
		// TODO Auto-generated method stub
		return isClient;
	}

	public boolean startClient() {
		model.setClient();
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DatagramPacket receivePacket;
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		try {
			clientSocket.receive(receivePacket);
			isClient = true;
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;

	}
	public void startClientLoop(){
		String loadedData;
		BoardFileHandler newHandler;
		DatagramPacket receivePacket;
		byte[] sendData = new byte[4096];
		byte[] receiveData = new byte[4096];
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		while (true) {
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			try {
				clientSocket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
				byte[] test = { 1, 1, 1, 1 };
				DatagramPacket senderPacket = new DatagramPacket(test, test.length, receivePacket.getAddress(),
						receivePacket.getPort());
				try {
					clientSocket.send(senderPacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Failed to send Packet");
				}
			}

		}
	}
}
