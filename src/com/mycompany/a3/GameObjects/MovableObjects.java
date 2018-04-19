package com.mycompany.a3.GameObjects;

import com.mycompany.a3.GameWorld;
import com.mycompany.a3.Interfaces.*;
import java.util.Random;

public abstract class MovableObjects extends GameObject implements IMovable{

	
	//movable objects have speed and heading (direction) 
	private int speed;
	private int direction; //direction or heading 
	
	//new movable object with staring location, color,  direction, speed
	MovableObjects(){
		
	    speed = ranSpeed();//random speed
		direction = randDirection(); //random direction  
		
	}
	
	public void move(){
		// compute the change in X and Y
		
		int theta_angle = 90 - direction;
		
		float deltaX =  (float)Math.cos(Math.toRadians(theta_angle)) * speed;
		float deltaY =  (float)Math.sin(Math.toRadians(theta_angle)) * speed;
		
		
		if ((deltaX + getLocation_x() + (30/2) > GameWorld.getMaxWidth()) || (deltaX + getLocation_x() - (30/2) < 0)){
			deltaX = -deltaX;
			setDirection(360-getDirection());
		}

		if ((deltaY + getLocation_y() + (30/2) > GameWorld.getMaxHeight()) || (deltaY + getLocation_y() - (30/2) < 0)){
			deltaY = -deltaY;
			setDirection(180-getDirection());
		}
		if(getLocation_x()==0){
			deltaX = -deltaX;
			setDirection(360-getDirection());
		}
		
		if(getLocation_y()==0){
			deltaY = -deltaY;
			setDirection(180-getDirection());
		}
		
		
	    float newLocationX = getLocation_x() + deltaX;
		float newLocationY = getLocation_y() + deltaY;
		setLocation_x(Math.round(newLocationX));
		setLocation_y(Math.round(newLocationY));
		
	}
	
    //get movable object's speed 
	public int getSpeed(){
		return speed;
	}
	
	//set the movable object's speed 
	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}

	//random speed for movable objects 
	public int ranSpeed(){
		int min_speed =  0;
		int max_speed =   10;
		Random rand = new Random();
		int randSpeed = rand.nextInt(max_speed - min_speed)  + min_speed;
		return randSpeed;
		
	}
		
	//get Movable object's direction 
	public int getDirection(){
		return direction;
	}
	
	//set movable object's direction 
	public void setDirection(int newDirection){
		direction = newDirection;
	}
	
	//random direction
	public int randDirection(){
		
		int min_drection =  0;
		int max_direction =   365;
		Random rand = new Random();
		int randDirection = rand.nextInt(max_direction - min_drection)  + min_drection;
		return randDirection;	
	}
	
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = " Speed="+ getSpeed() + " " +  " dir="+ getDirection() ;
		
		return parentDesc + myDesc; 
	}
}
