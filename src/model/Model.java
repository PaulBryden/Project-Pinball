package model;

import java.util.ArrayList;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class Model extends Observable{
	private ArrayList<HorizontalLine> lines;
	private Ball ball;
	private Walls gws;
	
	public Model(){
		
		ball = new Ball(25,25,100,100);
		
		gws = new Walls(0,0,500,500);
		
		lines = new ArrayList<HorizontalLine>();
		
	}
	
	public void moveBall(){
		double moveTime = 0.05;
		
		if(ball!=null && !ball.stopped()){
			CollisionDetails cd = timeUntilCollision();
			double tuc = cd.getTuc();
			if(tuc> moveTime){
				ball=movelBallForTime(ball, moveTime);
			}else{
				ball=movelBallForTime(ball,tuc);
				ball.setVelo(cd.getVelo());
			}
			this.setChanged();
			this.notifyObservers();
					
			
		}
	}
	public Ball movelBallForTime(Ball ball, double time) {
		double xVel = ball.getVelo().x();
		double yVel = ball.getVelo().y();
		double newX = ball.getExactX()+(xVel*time);
		double newY = ball.getExactY() + (yVel *time);
		ball.setExactX(newX);
		ball.setExactY(newY);
		return ball;
	}
	private CollisionDetails timeUntilCollision(){
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelo();
		Vect newVelo = new Vect(0,0);
		
		double shortestTime = Double.MAX_VALUE;
		double time = 0.0;
		
		ArrayList<LineSegment> lss = gws.getLineSegments();
		for (LineSegment line : lss){
			time=Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if(time<shortestTime){
				shortestTime = time;
				newVelo = Geometry.reflectWall(line, ball.getVelo(),1.0);
			}
		}
		
		for (HorizontalLine line : lines){
			LineSegment ls = line.getLineSeg();
			time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
			if(time < shortestTime){
				shortestTime = time;
				newVelo = Geometry.reflectWall(ls,  ball.getVelo(),1.0);
			}
		}
		return new CollisionDetails(shortestTime, newVelo);
	}
	
	public Ball getBall(){
		return ball;
		
	}
	
	public ArrayList<HorizontalLine> getLines(){
		return lines;
		
	}
	
	public void addLine(HorizontalLine lineSegment){
		lines.add(lineSegment);
	}
	
	public void setBallSpeed(int x, int y){
		ball.setVelo(new Vect(x,y));
	}
	
}
