package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandExit extends Command{
	
	GameWorld gw1;
	
	public CommandExit(GameWorld gw){
		
		super("Quit");
		gw1 = gw;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ew){
		gw1.quit();
		System.out.println("Quit button is invoked.");
		System.out.println();
		
	}
}
