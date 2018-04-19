package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

public class SoundCheckBox extends Command {
	
   GameWorld gw;
	
	public SoundCheckBox(GameWorld gw) {
     
		super("Sound ON/OFF");
		this.gw = gw;	
	}
	
	@Override
	public void actionPerformed(ActionEvent ew)
	{
	    System.out.println("Sound checkbox invoked.");
		gw.changeSoundOnOff();
	    System.out.println();
	}

}

