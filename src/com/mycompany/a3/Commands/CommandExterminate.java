package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandExterminate extends Command{
	
	GameWorld gw;
	
	public CommandExterminate(GameWorld gw) {
        super("Exterminate");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Astroids have collided each other and exterminated button is invoked.");
		gw.astroidsHaveCollidedEachOther();
		System.out.println();
		
	} 	
}
