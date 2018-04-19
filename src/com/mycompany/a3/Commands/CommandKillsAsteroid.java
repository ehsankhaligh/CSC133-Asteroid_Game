package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.mycompany.a3.*;
import com.codename1.ui.events.ActionEvent;

//command class for a button
public class CommandKillsAsteroid extends Command {
    GameWorld gw;

	public CommandKillsAsteroid(GameWorld gw){
		super("kill Asteroid");
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e){
		System.out.println("Missile killed astroid button is invoked.");
		gw.missileKilledAstroid();
		System.out.println();


	}
}
