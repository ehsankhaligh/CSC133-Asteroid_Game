package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandAddStation extends Command {
	
	GameWorld gw;
	
	public CommandAddStation (GameWorld gw){
		super("Add Station");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Add blinking space station button is invoked.");
		gw.addNewBlinkingSpaceStation();
		System.out.println();
		
	} 	
}
