package com.mycompany.a3.GameObjects;

import com.mycompany.a3.Interfaces.*;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SpaceStation extends FixedObjects implements IDrawable, ICollider{
   
	private int blinkRate;//each space station blinks on and off
	private boolean blink;
	
	public SpaceStation() {
		
		setColor(ColorUtil.rgb(0,191,255)); //set space station color 
		blinkRate = randBlinkRate(); //blink on and off in this rate on for example 2 second and off for 2 second 
		blink = true;
	}
	
	public boolean getBlink(){
		return blink;
	}
	
	public void setBlink(boolean NewBlink){
		blink = NewBlink;
	}
	
	public void Blink(){
		if(getBlink()==true){
			setBlink(false);
			//System.out.println("It blinked off");
		}else{
			setBlink(true);
			//System.out.println("It blinked on");
		}
	}
	
	public int getBlinkRate(){
		return blinkRate;
	}
	
	public void setBlinkRate(int NewBlinkRate){
		blinkRate = NewBlinkRate;
	}
	
	//generate random blink rate from 0 to 42 
	public int randBlinkRate(){
		int min_blinkRate =  0;
		int max_blinkRate =   42;
		Random rand = new Random();
		int randBlinkRate = rand.nextInt((max_blinkRate - min_blinkRate) + 1)  + min_blinkRate;
		return randBlinkRate;
	}

	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " rate=" + getBlinkRate();
		String station  = "Station:"; 
		return station + parentDesc + myDesc ;
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		int x = (int)(pCmpRelPrnt.getX()+ this.getLocation_x());
		int y = (int)(pCmpRelPrnt.getY()+ this.getLocation_y());
		int width=30, height=15;
		
		
		g.setColor(getColor());
		if(getBlink()==true){
			g.drawArc(x-7, y-9, 42, 30, 360, 360);
			g.drawRect(x,y,width,height);
		}else{
			g.drawArc(x-7, y-9, 42, 30, 360, 360);
			g.fillRect(x,y,width,height);
		}
		//System.out.println("Station's draw method Invoked.");
	
	}

    public boolean collidesWith(ICollider obj) {
		
    	boolean result = false;
		
		int thisCenterX = (int) (this.getLocation_x()); // find centers
		int thisCenterY = (int) (this.getLocation_y());
		
		int otherCenterX = (int) ((GameObject) obj).getLocation_x();
		int otherCenterY = (int) ((GameObject) obj).getLocation_y();
		
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		
		// find square of sum of radii
		int thisRadius =  15/2;
		int otherRadius = 15/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr+12000) { 
			result = true ; 
		}
		return result ;
   }  
}
