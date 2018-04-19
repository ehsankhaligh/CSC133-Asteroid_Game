package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandSpeedIncrease extends Command {
    GameWorld gw;
	
	public CommandSpeedIncrease (GameWorld gw){
		super("Increase Speed");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Speed increase button is invoked.");
		gw.increaseShipSpeed();
		System.out.println();
		
	} 	

}
