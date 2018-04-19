package com.mycompany.a3.GameObjects;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a3.GameWorld;


public abstract class GameObject {

	//all game objects have a location, color 
	private float location_x, location_y;
	private int color;
   
	//Gameobject constructor 
	GameObject(){
		location_x = randLocation_x(); 
		location_y = randLocation_y();
	}
	
	//get the object's current location x
	public float getLocation_x(){
		return location_x;
	}
	
	//set the object's current location x
	public void setLocation_x(float newLocation_x){
		location_x = newLocation_x;
	}

		
	// random location x
	public float randLocation_x(){
		
		int location_x_max =  GameWorld.getMaxWidth();
		int location_x_min =   0;
		Random rand = new Random();
		float randomLocationX = rand.nextInt(location_x_max - location_x_min)  + location_x_min;
		return randomLocationX;
	}
	
	//get the object's current location y
	public float getLocation_y(){
		return location_y;
	}
	
	//set the object's current location y
	public void setLocation_y(float newLocation_y){
		location_y = newLocation_y;
	}
		
	// Random y location
	public float randLocation_y(){
		int location_y_max =  GameWorld.getMaxHeight();
		int location_y_min =   0;
		Random rand = new Random();
		float randomLocationY = rand.nextInt(location_y_max - location_y_min)  + location_y_min;
		return randomLocationY;
	}
	
	public int getColor(){
		return color;
	}
	
	public void setColor(int NewColor){
		color = NewColor;
	}
	
	
	public String toString() {
		
		String myDesc = " loc=" + getLocation_x()+ "," + getLocation_y()+ " " + "color: " + "[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]";
		return myDesc; 
	}
	
		
}
