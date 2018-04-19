package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandJumpThrougHyperspace extends Command {
    GameWorld gw;
	
	public CommandJumpThrougHyperspace (GameWorld gw){
		super("Jump");
		this.gw = gw; 
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Jump throug hyperspacee button is invoked.");
		gw.jumpThrougHyperspace();
		System.out.println();
		
	} 	
}