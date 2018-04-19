package com.mycompany.a3.Views;

import com.mycompany.a3.Sounds.*;
import com.mycompany.a3.Interfaces.*;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.GameWorldProxy;
import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;
import com.codename1.ui.Container;

public class PointsView  extends Container implements Observer  {

	//new GUI Lables 
	private Label score;
	private Label score_get;
	private Label missiles;
	private Label missiles_get;
	private Label elapsed_time;
	private Label elapsed_time_get;
	private Label sound;
	private Label sound_get;
	private GameWorld gw;
	
	//BackgroundSound
	public BackGroundSound soundBackGround = new BackGroundSound("music.mp3");
	
	public PointsView(){ 
		
		
		score = new Label("Score:");
		score_get = new Label("---");
		missiles = new Label("Missiles:");
		missiles_get = new Label("----");
		elapsed_time = new Label("Elapsed time:");
		elapsed_time_get = new Label("------");
		sound = new Label("Sound:");
		sound_get = new Label("ON");
		
		
		score.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		score_get.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		
		missiles.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		missiles_get.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		
		elapsed_time.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		elapsed_time_get.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		elapsed_time_get.getAllStyles().setPaddingRight(2);
		elapsed_time_get.getAllStyles().setPaddingLeft(2);
		
		
		sound.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		sound_get.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
		sound_get.getAllStyles().setPaddingRight(2);
		sound_get.getAllStyles().setPaddingLeft(2);
		
		
		this.add(score);
		this.add(score_get);
		this.add(missiles);
		this.add(missiles_get);
		this.add(elapsed_time);
		this.add(elapsed_time_get);
		this.add(sound);
		this.add(sound_get);
		
        //BackGround Sound
		soundBackGround.play();
		
		
	}

	public void update(Observable observable, Object arg) {
		// code here to update labels from data in the Observable (a GameWorldPROXY)
		
		score_get.setText(Integer.toString(((IGameWorld)arg).getScore()));
		missiles_get.setText(Integer.toString(((IGameWorld)arg).getMissleCount1()));
		elapsed_time_get.setText(Integer.toString(((IGameWorld)arg).getClockTime()));
	
		//set Sound "ON" and "OFF" from side menue-bar 
			  if((((IGameWorld)observable).getSound() == true)){ 
				  sound_get.setText("OFF"); 
				  soundBackGround.pause();
			
			  }else{
				  //channge the sound to on only in playmode 
				  if(gw.getIsPlaying()==true){  
					  sound_get.setText("ON");
					  soundBackGround.play();
				  }
			  }	
	}
	 
}
