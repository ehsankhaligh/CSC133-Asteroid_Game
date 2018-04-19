package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;

import com.codename1.ui.events.ActionEvent;

public class CommandAbout extends Command {
    GameWorld gw;
	
	public CommandAbout (GameWorld gw){
		super("About");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
	    System.out.println("about button invoked.");	
		System.out.println();
		gw.about();	
	} 	
}
