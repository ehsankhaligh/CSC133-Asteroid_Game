package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

public class CommandNew extends Command {
    GameWorld gw;
	
	public CommandNew (GameWorld gw){
		super("New");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		//do nothing for this assignment 
	} 	
}