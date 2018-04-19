package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandSpeedDecrease extends Command {
    GameWorld gw;
	
	public CommandSpeedDecrease (GameWorld gw){
		super("Decrease Speed");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Speed decrease button is invoked.");
		gw.decreaseShipSpeed();
		System.out.println();
		
	} 	

}

