package com.mycompany.a3.GameObjects;

import com.mycompany.a3.Interfaces.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Ships extends MovableObjects implements ISteerable, IDrawable, ICollider{
    
	private int missileCount;
	private final int missileCountMax = 10;
	private int width=20, height=20;
	
	public Ships() {
		
		setLocation_x(441); //set ship x location 
		setLocation_y(335);//set ship y location 
		setSpeed(0);//set ship speed to zero
		setDirection(0);//set direction to north (0)
		setColor(ColorUtil.rgb(255,0,0)); // set ships color 

	}
	
	//get the ships's current missleCount
	public int getMissleCount(){
		return missileCount;
	}

	//set the ships's new missileCount 
	public void setMissleCount(int NewMissleCount){
		missileCount = NewMissleCount;	
	}
	

	//Ship turn left by user	
	public void turnLeft(){
		//change direction
		int turnleftship = getDirection() - 10;
		setDirection(turnleftship);
	}
	
	//Ship turn right by user
	public void turnRight(){
		//change direction
		int turnrightship = getDirection() + 10;;
		setDirection(turnrightship);
		
	}
	
	//Ship speed decrease by user
	public void SpeedIncrease(){	
		int speedInc = getSpeed() + 1;
		setSpeed(speedInc);
	}
	
	public void SpeedDecrease(){
		int speedDec = getSpeed() - 1;
		setSpeed(speedDec);
	}
	
	public int getMissileCountMax(){
		return missileCountMax;
	}
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " Missiles=" + getMissleCount();
		String ship = "ship:";
		return ship + parentDesc + myDesc; 
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {

		int x = (int)(pCmpRelPrnt.getX()+ this.getLocation_x());
		int y = (int)(pCmpRelPrnt.getY()+ this.getLocation_y());
		
		g.setColor(getColor());
		g.drawArc(x-5, y-5, 32, 32, 360, 360);
		g.fillRect(x,y,width,height);
				
	}

	public boolean collidesWith(ICollider obj) {
        
		boolean result = false;
		
		int thisCenterX = (int) this.getLocation_x(); // find centers
		int thisCenterY = (int) this.getLocation_y();
		
		int otherCenterX = (int) ((GameObject) obj).getLocation_x() ;
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