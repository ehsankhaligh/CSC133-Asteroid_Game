package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.mycompany.a3.Views.PointsView;
import com.codename1.ui.events.ActionEvent;

public class CommandPause extends Command {
    GameWorld gw;
	
	public CommandPause(GameWorld gw){
		super("Pause");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		
		System.out.println("Pause button is invoked.");
		gw.isPlaying();
		System.out.println("IsPlaying boolean status: "+ gw.getIsPlaying());
			
		
		//change pause button lable when it is pressed to play and vise versa 
		if(gw.getIsPlaying()==true){
			gw.changePlayToPauseButtonName();
		}else{
		    gw.changePauseToPlayButtonName();
		}
		
		//enable the refuel button when pause button pressed.
		//disable in play mode. 
        if(gw.getIsPlaying()==false){
            gw.RefuelBtnEnablePauseMode();           
            
 		}else{
 			gw.RefuelBtnDisablePauseMode();
 		}
	} 	
}