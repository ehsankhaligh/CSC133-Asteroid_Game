package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandLoadMissiles extends Command {
    GameWorld gw;
	
	public CommandLoadMissiles(GameWorld gw){
		super("Load Missiles");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Load new missiles in tohe ship button is invoked.");
		gw.loadNewMissilesIntoTheShip();
		System.out.println();
		
	} 	
}
