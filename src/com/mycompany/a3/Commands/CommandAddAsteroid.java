package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandAddAsteroid extends Command{
	
	GameWorld gw;
	
	public CommandAddAsteroid(GameWorld gw) {
        super("Add Asteroid");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Add asteroid button is invoked.");
		gw.addNewAstroid();
		System.out.println();
		
	} 	
}
