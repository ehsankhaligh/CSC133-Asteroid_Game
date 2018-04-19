package com.mycompany.a3;

import com.mycompany.a3.Interfaces.*;
import java.util.Observable;

/**A proxy which prohibits removal of GameWorldObjects from the GameWorld*/
public class GameWorldProxy extends Observable implements IGameWorld  {
	
	// code here to accept and hold a GameWorld, provide implementations
	// of all the public methods in a GameWorld, forward allowed
	// calls to the actual GameWorld, and reject calls to methods
	// which the outside should not be able to access in the GameWorld.
	
	private GameWorld realGameWorld ;
	
	public GameWorldProxy (GameWorld gw){ 
		realGameWorld = gw; 
		this.setChanged();
	}
	
	//return Score 
	public int getScore(){
		return realGameWorld.getScore();
	}
	
	//return MissleCount
	public int getMissleCount1(){
		return realGameWorld.getMissleCount1();
	}
	
	//return ClockTime()
	public int getClockTime(){
		return realGameWorld.getClockTime();
	}
	
	//return Sound
	public boolean getSound(){
		return realGameWorld.getSound();
	}
	
	public void printMapCurrentWorldState(){
		realGameWorld.printMapCurrentWorldState();
	}
}