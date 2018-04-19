package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandCrashShip extends Command{
	
	GameWorld gw;
	
	public CommandCrashShip(GameWorld gw) {
        super("Crash Ship");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Ship crashed into astroid button is invoked.");
		gw.shipCrashedIntoAstroid();
		System.out.println();
		
	} 	
}
