package model;

import physics.LineSegment;

public class HorizontalLine {
	private int xpos;
	private int ypos;
	private int width;
	private LineSegment ls;
	
	public HorizontalLine(int x, int y, int w){
		xpos=x;
		ypos=y;
		width=w;
		ls=new LineSegment(xpos, ypos, xpos+width, ypos);
	}
	public LineSegment getLineSeg(){
		return ls;
	}
	public int getX() {
		// TODO Auto-generated method stub
		return xpos;
	}
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	public int getY() {
		// TODO Auto-generated method stub
		return ypos;
	}
}
