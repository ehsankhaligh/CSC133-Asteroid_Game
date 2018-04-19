package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandAddShip extends Command {
    GameWorld gw;
	
	public CommandAddShip (GameWorld gw){
		super("Add Ship");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Add new ship button is invoked.");
		gw.addNewShip();
		System.out.println();
		
	} 	
}
