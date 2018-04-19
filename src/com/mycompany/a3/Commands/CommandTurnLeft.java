package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandTurnLeft extends Command {
    GameWorld gw;
	
	public CommandTurnLeft (GameWorld gw){
		super("Turn Left");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Trurn left button is invoked.");
		gw.shipTurnLeft();
		System.out.println();
		
	} 	
}
