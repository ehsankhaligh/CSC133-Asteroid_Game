package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

public class CommandTick extends Command {
	
	GameWorld gw;
	
	public CommandTick (GameWorld gw){
		super("Tick");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("game clock has ticked button is invoked.");
		gw.gameClockHasTicked();
		System.out.println();
		
	} 	
}