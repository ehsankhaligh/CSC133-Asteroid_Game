package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandTurnRight extends Command {
    GameWorld gw;
	
	public CommandTurnRight (GameWorld gw){
		super("Turn Right");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Turn right button is invoked.");
		gw.shipTurnRight();;
		System.out.println();
		
	} 	

}