package com.mycompany.a3.GameObjects;

import com.mycompany.a3.Interfaces.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import java.util.Random;

public class Asteroids extends MovableObjects implements IDrawable, ISelectable, ICollider{

	private int size;
	private boolean containFlag=true;
	
	
	public Asteroids() {
		
		setColor(ColorUtil.rgb(124,252,0));//set Asteroids color
		size = randsize();
		
	
	}
	//get Ship size 
	public int getSize(){
		return size;
	}
	//set ship size
	public void setSize(int newSize){
		size = newSize;
	}  
	
	//create random size for asteroids 
	public int randsize(){

		int min_size =  15;
		int max_size =   25;
		Random rand = new Random();
		int randsize = rand.nextInt((max_size - min_size)  + 1) + min_size;
		return randsize;
	}
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " size=" + getSize();
		String astroid = "Asteroid: ";
		return astroid+ parentDesc + myDesc;
	}
	
	private boolean isSelected;
	public void setSelected(boolean yesNo) { isSelected = yesNo; }
	public boolean isSelected() { return isSelected; }

	public void draw(Graphics g, Point pCmpRelPrnt) {
	 	
		int x = (int)(pCmpRelPrnt.getX()+ this.getLocation_x());
		int y = (int)(pCmpRelPrnt.getY()+ this.getLocation_y());
		int startAngle=360, arcAngle=360;
		
		g.setColor(getColor()); 
		
	   if(isSelected()) {
		   g.drawArc(x, y, getSize(), getSize(), startAngle, arcAngle);
	   }
	   else{
		   g.fillArc(x, y, getSize(), getSize(), startAngle, arcAngle);
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

	  
		/*System.out.println("Px:"+px);
		System.out.println("Py:"+py);
		System.out.println("xloc:"+xLoc);
		System.out.println("yloc:"+yLoc);
		System.out.println("size:"+getSize());
		int value = xLoc+getSize();
		int value1 = yLoc+getSize();
		System.out.println("xLoc+getSize()="+ value);
		System.out.println("yLoc+getSize()="+ value1);
		System.out.println((int)this.getLocation_x());
		System.out.println((int)this.getLocation_y());*/
		
		if((xLoc<=px&&px<=xLoc+getSize())&&(yLoc<=py&&py<=yLoc+getSize())){
				setContainFlag(false);
		 }else{
				setContainFlag(true);
		 }
		 
	      
	    return getContainFlag();   
		
	}
   
    
	public boolean collidesWith(ICollider obj) {
        boolean result = false;

		int thisCenterX = (int) (this.getLocation_x());  // find centers
		int thisCenterY = (int) (this.getLocation_y()); 
		int otherCenterX = (int) (((GameObject) obj).getLocation_x()); 
		int otherCenterY = (int) (((GameObject) obj).getLocation_y());
		// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		// find square of sum of radii
		int thisRadius = this.getSize()/2;
		int otherRadius = this.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) { 
			result = true ; 
		}
		return result ;
		
	}
	
}
