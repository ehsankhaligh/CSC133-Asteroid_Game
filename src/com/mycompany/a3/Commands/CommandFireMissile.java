package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.*;

//command class for a button
public class CommandFireMissile extends Command {
    GameWorld gw;
	
	public CommandFireMissile (GameWorld gw){
		super("Fire a missile");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("fire missile button is invoked.");
		gw.fireMissileShip();
		System.out.println();
		
	} 	
}