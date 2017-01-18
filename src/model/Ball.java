package model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

public class Ball {
	private Vect velocity;
	private double radius;
	private double xpos;
	private double ypos;
	private Color colour;
	
	private boolean stopped;
	
	public Ball(double x, double y, double xv, double yv){
		xpos=x; //centre co-ordinates
		ypos=y;
		colour = Color.BLUE;
		velocity = new Vect(xv,yv);
		radius = 10;
		stopped=false;
	}
	public Vect getVelo(){
		return velocity;
	}
	public void setVelo(Vect v){
		velocity=v;
	}
	public double getRadius(){
		return radius;
	}
	public Circle getCircle(){
		return new Circle(xpos,ypos,radius);
	}
	public boolean stopped() {
		// TODO Auto-generated method stub
		return stopped;
	}
	public Color getColour() {
		// TODO Auto-generated method stub
		return colour;
	}
	public double getExactX() {
		// TODO Auto-generated method stub
		return xpos;
	}
	public double getExactY() {
		// TODO Auto-generated method stub
		return ypos;
	}
	public void setExactX(double newX) {
		// TODO Auto-generated method stub
		xpos=newX;
	}
	public void setExactY(double newY) {
		// TODO Auto-generated method stub
		ypos=newY;
	}
}
