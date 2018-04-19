package com.mycompany.a3.GameObjects;

import com.mycompany.a3.Interfaces.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Missiles extends MovableObjects implements IDrawable, ISelectable, ICollider{
	
	private int fuelLevel; // Missiles's fuel level
	private boolean containFlag=true;
	private int width=5,height=60;

	
	public Missiles(float ship_x,float ship_y, int ship_direction) { 
		fuelLevel = 100; // set the initial to 10
		setColor(ColorUtil.rgb(237, 2, 233));//set missiles color
		setLocation_x(ship_x); //set Missiles x location match ship's location
		setLocation_y(ship_y); // set Missiles y location match ship's location	
		setDirection(ship_direction);
		setSpeed(1);//set Missiles speed greater than ships speed 
	}
	
	//get Missiles's fuel level
	public int getFuelLevel(){
			return fuelLevel;
	}
	
	// add more fuel to the car
	public void SetFuel(int NewFuelLevel){
		fuelLevel = NewFuelLevel;
	}

	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " fuel=" + getFuelLevel() ;
		String missile =  "Missile"; 
		return missile + parentDesc + myDesc ;
	}

	private boolean isSelected;
	public void setSelected(boolean yesNo) { isSelected = yesNo; }
	public boolean isSelected() { return isSelected; }

	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		int x = (int)this.getLocation_x();
		int y = (int)this.getLocation_y();
		int width=5,height=60;
		
		if(isSelected()) {
			g.setColor(getColor());	
			g.drawArc(x-10, y-5, 32, 70, 360, 360);
			g.fillRect(x,y,width,height);
		}
		else{
			g.setColor(ColorUtil.rgb(75,0,130));
			g.drawArc(x-10, y-5, 32, 70, 360, 360);
			g.fillRect(x,y,width,height);
		}
		
	}
	
	public void setContainFlag(boolean newContainFlag){
		this.containFlag = newContainFlag;
	}
	
	public boolean getContainFlag(){
		return containFlag;
	}
    public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {

        int iShapeX=(int)this.getLocation_x(); 
    	int iShapeY=(int)this.getLocation_y(); 
		int px = pPtrRelPrnt.getX(); // pointer location relative to
		int py = pPtrRelPrnt.getY(); // parent�s origin
		int xLoc = (pCmpRelPrnt.getX())+iShapeX ;// shape location relative
		int yLoc = (pCmpRelPrnt.getY())+iShapeY ;// to parent�s origin
		
		if((xLoc<=px&&px<=xLoc+width)&&(yLoc<=py&&py<=yLoc+height)){
				setContainFlag(false);
		 }else{
				setContainFlag(true);
		 }
		 
	      
	    return getContainFlag();   
		
	}

	public boolean collidesWith(ICollider obj) {
        
		boolean result = false;
		
		int thisCenterX = (int) this.getLocation_x(); // find centers
		int thisCenterY = (int) this.getLocation_y();// find centers
		
		int otherCenterX = (int) ((GameObject) obj).getLocation_x();
		int otherCenterY = (int) ((GameObject) obj).getLocation_y();
		
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		
		// find square of sum of radii
		int thisRadius = 32/2;
		int otherRadius = 32/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true ; 
		}
		return result ;
	}

}
