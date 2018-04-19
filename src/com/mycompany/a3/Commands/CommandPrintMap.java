package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

public class CommandPrintMap extends Command {
    GameWorld gw;
	
	public CommandPrintMap (GameWorld gw){
		super("map");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		gw.printMapCurrentWorldState();
	} 	
}