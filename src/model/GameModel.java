package model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.Geometry.VectPair;
import physics.LineSegment;
import physics.Vect;

public class GameModel extends Observable implements IModel, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -534290548267481179L;
	BoardFileHandler fileHandler;
	private List<IGizmo> gizmos;
	private List<IBall> balls;
	private Map<Integer, ITrigger> keyPressedTriggers;
	private Map<Integer, ITrigger> keyReleasedTriggers;
	private boolean pauseGame = false;
	private Color backgroundColour;
	private CollisionEvaluator collisionEvaluator;
	private PhysicsEvaluator physicsEvaluator;
    DatagramSocket serverSocket;
    byte[] receiveData = new byte[2048];
    byte[] sendData = new byte[2048];
    boolean isHost;
    boolean isClient;
    InetAddress returnAddr;
    HashMap<InetAddress,Integer> listOfClients;
    ArrayList<Integer> keysToSend;
	public GameModel() {

		listOfClients=new HashMap<>();
		reset();
	}

	public void tick() {
		// Evaluate collisions for all items in Gizmolist
		collisionEvaluator.evaluate();
		double tick = collisionEvaluator.getTickTime();
		// Move all items based on that tick time
		for (IBall ball : balls) {
			ball.moveForTime(tick);
		}
		for (IFlipper flipper : getFlippers()) {
			flipper.moveForTime(tick);
		}
		// Resolve collision
		
		collisionEvaluator.resolveCollision();
		// Apply friction and gravity
		physicsEvaluator.applyGravity(tick);
		physicsEvaluator.applyFriction(tick);
		
		// Update view
		setChanged();
		notifyObservers();
		if(isHost){
			
            try {

		          BoardFileHandler newHandler = new BoardFileHandler(this);
				sendData = newHandler.saveToString().getBytes();
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
	}

	public synchronized List<IFlipper>  getFlippers() {
		List<IFlipper> flippers = new LinkedList<>();
		for (IGizmo gizmo : gizmos) {
			if (gizmo instanceof IFlipper) {
				flippers.add((IFlipper) gizmo);
			}
		}
		return flippers;
	}

	public List<IGizmo> getGizmos() {
		return gizmos;
	}

	public void addGizmo(IGizmo gizmo) {
		gizmos.add(gizmo);
	}

	public void addBall(IBall ball) {
		balls.add(ball);
	}

	public void removeGizmo(IGizmo gizmo) {
		gizmos.remove(gizmo);
	}
	public void setGizmos(List<IGizmo> gizmos){
		this.gizmos=gizmos;
	}
	public void setBalls(List<IBall> balls){
		this.balls=balls;
	}

	public void reset() {
		gizmos = new LinkedList<>();
		gizmos.add(new Wall(0, 0, 0, 20));
		gizmos.add(new Wall(0, 0, 20, 0));
		gizmos.add(new Wall(20, 0, 20, 20));
		gizmos.add(new Wall(0, 20, 20, 20));

		balls = new LinkedList<>();

		keyPressedTriggers = new HashMap<>();
		keyReleasedTriggers = new HashMap<>();

		backgroundColour = Constants.BACKGROUND_DEFAULT_COLOUR;
		collisionEvaluator = new CollisionEvaluator(this);
		physicsEvaluator = new PhysicsEvaluator(this);
	}

	public List<IBall> getBalls() {
		return balls;
	}

	@Override
	public void processKeyPressedTrigger(int keyCode) {
		if (keyPressedTriggers.containsKey(keyCode)) {
			keyPressedTriggers.get(keyCode).triggerConnectedGizmos();
		}
	}

	@Override
	public void processKeyReleasedTrigger(int keyCode) {
		if (keyReleasedTriggers.containsKey(keyCode)) {
			keyReleasedTriggers.get(keyCode).triggerConnectedGizmos();
		}
	}

	@Override
	public void addKeyPressedTrigger(int keyCode, IGizmo gizmo) {
		if (keyPressedTriggers.containsKey(keyCode)) {
			keyPressedTriggers.get(keyCode).addGizmoToTrigger(gizmo);
		} else {
			keyPressedTriggers.put(keyCode, new KeyTrigger(gizmo));
		}
	}

	@Override
	public void addKeyReleasedTrigger(int keyCode, IGizmo gizmo) {
		if (keyReleasedTriggers.containsKey(keyCode)) {
			keyReleasedTriggers.get(keyCode).addGizmoToTrigger(gizmo);
		} else {
			keyReleasedTriggers.put(keyCode, new KeyTrigger(gizmo));
		}
	}

	@Override
	public Color getBackgroundColour() {
		return this.backgroundColour;
	}

	@Override
	public void setBackgroundColour(Color colour) {
		this.backgroundColour = colour;
	}

	public Map<Integer, ITrigger> getKeyPressedTriggers() {
		return keyPressedTriggers;
	}

	public Map<Integer, ITrigger> getKeyReleasedTriggers() {
		return keyReleasedTriggers;
	}
	public void startHosting(){
		try {
			serverSocket= new DatagramSocket(1003);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to bind Port");
		}
		isHost=true;
		boolean Response=false;
		while(!Response){
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
			serverSocket.receive(receivePacket);
			Response=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("RECEIVED:");
        System.out.println(receivePacket.getAddress());
        System.out.println(receivePacket.getPort());

        listOfClients.put(receivePacket.getAddress(), receivePacket.getPort());
		}
		}
	
	public void startClient(){
		
		BufferedReader inFromUser =
		         new BufferedReader(new InputStreamReader(System.in));
		      DatagramSocket clientSocket = null;
			try {
				clientSocket = new DatagramSocket();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      InetAddress IPAddress=null;
			try {
				IPAddress = InetAddress.getByName("localhost");
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      byte[] sendData = new byte[2048];
		      byte[] receiveData = new byte[2048];
		     sendData[0]='C';
		      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1003);
		      try {
				clientSocket.send(sendPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      DatagramPacket receivePacket;
		      String loadedData;
		      BoardFileHandler newHandler;
		      isClient=true;
		      while(true){
		      receivePacket = new DatagramPacket(receiveData, receiveData.length);
		      try {
				clientSocket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		           loadedData = new String(receivePacket.getData());
		          System.out.println(loadedData);
		           newHandler = new BoardFileHandler(this);
		          try {
		        	
					newHandler.loadFromString(loadedData);
					System.out.println(gizmos.size());

					setChanged();
					notifyObservers();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      
			}
	}
	public  void deserializeList(byte[] data) throws IOException, ClassNotFoundException {
	    System.out.println(data.toString());
		ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    SquareGizmo gizmoModel = (SquareGizmo) is.readObject();
	    System.out.println(gizmoModel.getGridCoords().x());
	    gizmos.add(gizmoModel);

	}
	
	public void addKeyToSend(int keyCode){
		keysToSend.add(keyCode);
	}

	@Override
	public void run() {
		this.startClient();
	}

	@Override
	public boolean isClient() {
		// TODO Auto-generated method stub
		return isClient;
	}
}
