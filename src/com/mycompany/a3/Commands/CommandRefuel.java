package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class CommandRefuel extends Command {
    GameWorld gw;
    
	public CommandRefuel (GameWorld gw){
		super("Fuel");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
        System.out.println("Fuel button invoked.");
        if(gw.getIsPlaying()==false){
           gw.MissileRefuelPauseMode();
		}
	} 	
}