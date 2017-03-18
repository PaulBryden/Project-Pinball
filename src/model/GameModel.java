package model;

import java.awt.Color;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import network.Client;
import network.Host;
import physics.Vect;

/**
 * 
 * @author Matt, David, Paul
 *
 */
class GameModel extends Observable implements IModel {

	BoardFileHandler fileHandler;
	private List<IGizmo> gizmos;
	private List<IBall> balls;
	private Map<Integer, KeyTrigger> keyPressedTriggers;
	private Map<Integer, KeyTrigger> keyReleasedTriggers;
	private Color backgroundColour;
	private Color foregroundColour;
	private CollisionEvaluator collisionEvaluator;
	private PhysicsEvaluator physicsEvaluator;

	boolean isClient;
	public Deque<String> keysToSend;
	private Host host=null;
	private Client client=null;
	private double gravity;
	private double mu;
	private double mu2;

	/**
	 * Create a new model with default physics and walls.
	 */
	public GameModel() {
		keysToSend = new ArrayDeque<String>();
		reset();
		setDefaultPhysics();
	}

	@Override
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
		for (IAbsorber absorber : getAbsorbers()) {
			absorber.updateFiring();
		}
		
		// Resolve collision
		collisionEvaluator.resolveCollision();
		// Apply friction and gravity
		physicsEvaluator.applyGravity(tick);
		physicsEvaluator.applyFriction(tick);

		// Update view
		setChanged();
		notifyObservers();
		sendTick();
	}

	private void sendTick() {
		// TODO Auto-generated method stub
		if (host!=null) {
			host.sendBoard();
		
				if(!host.receiveKeys()){
					//Timeout error, abort and reset
					host=null;
				}
			
			
		}
	}

	@Override
	public synchronized List<IFlipper> getFlippers() {
		List<IFlipper> flippers = new LinkedList<>();
		for (IGizmo gizmo : gizmos) {
			if (gizmo instanceof IFlipper) {
				flippers.add((IFlipper) gizmo);
			}
		}
		return flippers;
	}

	@Override
	public List<IAbsorber> getAbsorbers() {
		List<IAbsorber> absorbers = new LinkedList<>();
		for (IGizmo gizmo : gizmos) {
			if (gizmo instanceof IAbsorber) {
				absorbers.add((IAbsorber) gizmo);
			}
		}
		return absorbers;
	}

	@Override
	public IBall getBall(Vect coords) {
		for (IBall ball : balls) {
			Vect pos = ball.getCentre();
			double r = ball.getRadius();
			if (pos.x() + r > coords.x() && pos.x() - r < coords.x() + 1 && pos.y() + r > coords.y()
					&& pos.y() - r < coords.y() + 1)
				return ball;
		}
		return null;
	}

	@Override
	public IGizmo getGizmo(Vect coords) {
		for (IGizmo gizmo : gizmos) {
			Vect gizmoCoords = gizmo.getGridCoords();
			int width = gizmo.getGridWidth();
			int height = gizmo.getGridHeight();
			if (gizmoCoords != null && coords.x() >= gizmoCoords.x() && coords.x() < gizmoCoords.x() + width
					&& coords.y() >= gizmoCoords.y() && coords.y() < gizmoCoords.y() + height) {
				return gizmo;
			}
		}
		return null;
	}

	@Override
	public boolean isCellEmpty(Vect coords) {
		return getGizmo(coords) == null && getBall(coords) == null;
	}

	@Override
	public List<IGizmo> getGizmos() {
		return gizmos;
	}

	@Override
	public void addGizmo(IGizmo gizmo) {
		gizmos.add(gizmo);
	}

	@Override
	public void addBall(IBall ball) {
		balls.add(ball);
	}

	@Override
	public void removeBall(IBall ball) {
		balls.remove(ball);
	}

	@Override
	public void removeGizmo(IGizmo gizmo) {
		gizmos.remove(gizmo);
	}

	@Override
	public void setGizmos(List<IGizmo> gizmos) {
		this.gizmos = gizmos;
	}

	@Override
	public void setBalls(List<IBall> balls) {
		this.balls = balls;
	}

	@Override
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
		foregroundColour = Constants.FOREGROUND_DEFAULT_COLOUR;
		collisionEvaluator = new CollisionEvaluator(this);
		physicsEvaluator = new PhysicsEvaluator(this);
	}

	@Override
	public List<IBall> getBalls() {
		return balls;
	}

	@Override
	public  void processKeyPressedTrigger(int keyCode) {
		if (keyPressedTriggers.containsKey(keyCode)) {
			keyPressedTriggers.get(keyCode).triggerConnectedGizmos();
		}
	}

	@Override
	public  void processKeyReleasedTrigger(int keyCode) {
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
	public Color getForegroundColour() {
		return this.foregroundColour;
	}

	@Override
	public void setBackgroundColour(Color colour) {
		this.backgroundColour = colour;
	}
	
	@Override
	public void setForegroundColour(Color colour) {
		this.foregroundColour = colour;
	}

	@Override
	public Map<Integer, KeyTrigger> getKeyPressedTriggers() {
		return keyPressedTriggers;
	}

	@Override
	public Map<Integer, KeyTrigger> getKeyReleasedTriggers() {
		return keyReleasedTriggers;
	}

	@Override
	public void addKeyToSend(String keyData) {
		keysToSend.add(keyData);
	}

	@Override
	public double getGravity() {
		return gravity;
	}

	@Override
	public double getFrictionMu() {
		return mu;
	}

	@Override
	public double getFrictionMu2() {
		return mu2;
	}

	@Override
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	@Override
	public void setFrictionMu(double mu) {
		this.mu = mu;
	}

	@Override
	public void setFrictionMu2(double mu2) {
		this.mu2 = mu2;
	}

	@Override
	public void update() {
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean isClient() {
		return !(client==null);
	}

	@Override
	public void setClient(Client client) {
		this.client=client;
	}
	@Override
	public Client getClient() {
		return client;
	}
	

	@Override
	public void setHost(Host host) {
		this.host=host; 
	}
	@Override
	public Host getHost() {
		return this.host;
	}

	@Override
	public Deque<String> getKeysToSend() {
		return keysToSend;
	}

	@Override
	public void setDefaultPhysics() {
		this.gravity = Constants.DEFAULT_GRAVITY;
		this.mu = Constants.DEFAULT_MU;
		this.mu2 = Constants.DEFAULT_MU2;
	}

}
