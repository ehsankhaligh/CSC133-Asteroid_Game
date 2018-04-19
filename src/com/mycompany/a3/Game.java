package com.mycompany.a3;

import com.mycompany.a3.Views.*;
import com.mycompany.a3.Commands.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;

import java.io.InputStream;

import com.codename1.charts.util.ColorUtil;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;

	
//Controller
//Actions player may Take and what happens 
public class Game extends Form implements Runnable{

	private GameWorld gw;
	private GameWorldProxy gwp;
	private MapView mv; // new in a3
	private PointsView pv; // new in a3
	private Media m;
	
	
    //Add buttons 	
	private Button addAsteeroid_btn = new Button();
	private Button addStation_btn = new Button();
	private Button addShipd_btn = new Button();
	private Button increaseSpeed_btn = new Button();
	private Button decreaseSpeed_btn = new Button();
	private Button left_btn = new Button();
	private Button right_btn = new Button();
	private Button fire_btn = new Button();
	private Button jump_btn = new Button();
	private Button loadMissiles_btn = new Button();
	private Button killsAsteroid_btn = new Button();
	private Button crashShip_btn = new Button();
	private Button exterminate_btn = new Button();
	private Button tick_btn = new Button();
	private static Button refuel_btn = new Button(); //make this button static to change in for pause mode in commadRefuel class
	private static Button pause_btn = new Button(); //make static to change name from play to pause and vise versa in play mode 
	private Button quit_btn = new Button();
	
	public Game(){
		
		// code here to create menus, create Command objects for each command,
		// add commands to Command menu, create a control panel for the buttons,
		// add buttons to the control panel, add commands to the buttons, and
		// add control panel, MapView panel, and PointsView panel to the form
		
		gw = new GameWorld(); // create “Observable”
		gw.init();
		mv = new MapView(); // create an “Observer” for the map
		pv = new PointsView();// create an “Observer” for the points
		gw.addObserver(pv); // register the points observer
		gw.setIsPlaying(true);//set initaial state of game to true to start 


		//layout
		setLayout(new BorderLayout());
		
		//add toolbar
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		this.setTitle("Asteroids");
		
		//add container 
		Container topContainer = new Container();
		topContainer.add(pv);
		topContainer.setLayout(new FlowLayout(Component.CENTER));
		add(BorderLayout.NORTH, topContainer);
		
		//left container 
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		add(BorderLayout.WEST,leftContainer);
		
		//Bottom container 
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		add(BorderLayout.SOUTH,bottomContainer);
		
		//Center container 
		Container centerContainer = new Container();
		centerContainer.getAllStyles().setBgTransparency(255); 
		centerContainer.getAllStyles().setBgColor(ColorUtil.WHITE);
		centerContainer.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		add(BorderLayout.CENTER,centerContainer);
	

		
		//commands label
		Label commands = new Label("Commands:  ");
		commands.getUnselectedStyle().setBgTransparency(255);
		leftContainer.add(commands);

		//add Asteroid button
		addAsteeroid_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		addAsteeroid_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		addAsteeroid_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(addAsteeroid_btn);//add command 
		addAsteeroid_btn.setCommand(new CommandAddAsteroid(gw));
		
		//addStation button
		addStation_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		addStation_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		addStation_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(addStation_btn);
		addStation_btn.setCommand(new CommandAddStation(gw));//add command 
		
		//addShipd button
		addShipd_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		addShipd_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		addShipd_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(addShipd_btn);
		addShipd_btn.setCommand(new CommandAddShip(gw));//add command

		//increase speed button 
		increaseSpeed_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		increaseSpeed_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		increaseSpeed_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(increaseSpeed_btn);
		CommandSpeedIncrease myCommandSpeedIncrease = new CommandSpeedIncrease(gw);
		increaseSpeed_btn.setCommand(myCommandSpeedIncrease);//add command
		addKeyListener('i', myCommandSpeedIncrease);//keboard 
		
		//decrease speed button 
		decreaseSpeed_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		decreaseSpeed_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		decreaseSpeed_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(decreaseSpeed_btn);
		CommandSpeedDecrease myCommandSpeedDecrease = new CommandSpeedDecrease(gw);
		decreaseSpeed_btn.setCommand(myCommandSpeedDecrease);//add command
		addKeyListener('d', myCommandSpeedDecrease);//keboard  
		
		//turn left button 
		left_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		left_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		left_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(left_btn);
		CommandTurnLeft myCommandTurnLeft = new CommandTurnLeft(gw);
		left_btn.setCommand(myCommandTurnLeft);//add command
		addKeyListener('l', myCommandTurnLeft);//keyboard
		
		//turn right button 
		right_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		right_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		right_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(right_btn);
		CommandTurnRight myCommandTurnRight= new CommandTurnRight(gw);
		right_btn.setCommand(myCommandTurnRight);//add command
		addKeyListener('r', myCommandTurnRight);//keboard 
		
		//fire a missile button 
		fire_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		fire_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		fire_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(fire_btn);
		CommandFireMissile myCommandFireMissile = new CommandFireMissile(gw);
		fire_btn.setCommand(myCommandFireMissile);//add command
		addKeyListener('f', myCommandFireMissile);
		
		//jump button
		jump_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		jump_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		jump_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(jump_btn);
		CommandJumpThrougHyperspace myCommandJumpThrougHyperspace = new CommandJumpThrougHyperspace(gw);
		jump_btn.setCommand(myCommandJumpThrougHyperspace);//add command
		addKeyListener('j', myCommandJumpThrougHyperspace);
		
		//map
		CommandPrintMap myCommandPrintMap = new CommandPrintMap(gw);
		addKeyListener('m', myCommandPrintMap);
		
		//load missile button 
		loadMissiles_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		loadMissiles_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		loadMissiles_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		//leftContainer.add(loadMissiles_btn);
		//loadMissiles_btn.setCommand(new CommandLoadMissiles(gw));//add command
		addKeyListener('o', new CommandLoadMissiles(gw));
		
		
		//kill asteroid button 
		killsAsteroid_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		killsAsteroid_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		killsAsteroid_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		//leftContainer.add(killsAsteroid_btn);
		//killsAsteroid_btn.setCommand(new CommandKillsAsteroid(gw));//add command
		addKeyListener('k', new CommandKillsAsteroid(gw));

		//crash ship button
		crashShip_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		crashShip_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		crashShip_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		//leftContainer.add(crashShip_btn);
		//crashShip_btn.setCommand(new CommandCrashShip(gw));//add command
		addKeyListener('c', new CommandCrashShip(gw));
 
		//exterminate button
		exterminate_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		exterminate_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		exterminate_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		//leftContainer.add(exterminate_btn);
		//exterminate_btn.setCommand(new CommandExterminate(gw));//add command
		addKeyListener('x', new CommandExterminate(gw));
		
		//tick button 
		tick_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		tick_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		tick_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		//leftContainer.add(tick_btn);
		//tick_btn.setCommand(new CommandTick(gw));//add command
		addKeyListener('t', new CommandTick(gw));
		
		//refuel button 
		refuel_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		refuel_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		refuel_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(refuel_btn);
		refuel_btn.setCommand(new CommandRefuel(gw));//add comman
		refuel_btn.setEnabled(false);//enable after game starts in pause mode 
		
		//Pause button 
		pause_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		pause_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		pause_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(pause_btn);
		pause_btn.setCommand(new CommandPause(gw));//add command
		
		
		//quit button 
		quit_btn.getUnselectedStyle().setBgTransparency(255);
		//add style
		quit_btn.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		quit_btn.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftContainer.add(quit_btn);
		quit_btn.setCommand(new CommandExit(gw));//add command

		//add file side menu options
		Command file = new Command("File side-MenuBar");
		myToolbar.addCommandToLeftBar(file);
		
		Command sideMenuItemCheck = new Command("Side Menu Item Check");
		CheckBox checkSideMenuComp = new CheckBox("Sound ON/OFF"); //sound checkbox 
		//set the style of the check box
		checkSideMenuComp.getAllStyles().setBgTransparency(255); 
		checkSideMenuComp.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		sideMenuItemCheck.putClientProperty("SideComponent", checkSideMenuComp);
		myToolbar.addCommandToSideMenu(sideMenuItemCheck);
		checkSideMenuComp.setCommand(new SoundCheckBox(gw));
		
		//add file side menu options 
		myToolbar.addCommandToSideMenu(new CommandNew(gw));//new command
		myToolbar.addCommandToSideMenu(new CommandSave(gw));//Save Command
		myToolbar.addCommandToSideMenu(new CommandUndo(gw));//Undo Command
		myToolbar.addCommandToSideMenu(new CommandAbout(gw));//About Command 
		myToolbar.addCommandToSideMenu(new CommandExit(gw));//Exit Command 
		
		this.show();
		
		//center container get height or width
		gw.setDimentions((int)centerContainer.getWidth(),(int)centerContainer.getHeight());
		
		//System.out.println("centerContainer Width:"+(int)centerContainer.getWidth());
		//System.out.println("centerContainer Height:"+(int)centerContainer.getHeight());
		//System.out.println("GameWorld width:"+GameWorld.getMaxWidth());
		//System.out.println("GameWorld Height:"+GameWorld.getMaxWidth());
		
		mv.setWidth(centerContainer.getWidth());
		mv.setHeight(centerContainer.getHeight());
		
		gw.addObserver(mv);
		centerContainer.add(mv);
		
		// create a Timer and schedule it
		UITimer timer = new UITimer (this);
		timer.schedule(100, true, this);
		
		this.show();
	
	}
	//created this method to use in GameWorld class to change the button in proxy 
	public static Button refuel_btn(){
		return refuel_btn;
	}
	
	public static Button pause_btn(){
		return pause_btn;
	}
	
	public void run() {
	   
		//calles game tick to tell objects to move 
       if(gw.getIsPlaying()==true){
		  gw.gameClockHasTicked();
		  mv.repaint();
       }

	}
}
