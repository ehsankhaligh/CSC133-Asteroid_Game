package com.mycompany.a3;

import com.mycompany.a3.Interfaces.*;
import com.mycompany.a3.Sounds.Sound;
import com.mycompany.a3.GameObjects.*;
import java.util.Observable;
import java.util.Vector;

import com.codename1.ui.Dialog;

//data model,observable
//containing a collection of game objects and other game state values 
public class GameWorld  extends Observable implements IGameWorld {
	
   private static int maxWidth = 883;//size of width    
   private static int maxHeight = 671;//size of height 
   private static int livesleft = 3; //how many lives left
   private static int clockTime = 0; //keeps track of time
   private static int score = 0; //keep track of score
   public static boolean isPlaying;//for pausing the Game
   boolean AsteroidRemove = false;//For Collision
   boolean MissileRemove = false;//For Collision
   boolean shipRemove = false;//For Collision
   private boolean sound;
   
   //Collision Vector to keep track
   private Vector<GameObject> AsteroidCollide = new Vector<GameObject>();
   private Vector<GameObject> ShipCollide = new Vector<GameObject>();
   private Vector<GameObject> MissileCollide = new Vector<GameObject>();
   //Instantiate objects  
   private Ships addship = new Ships();
   private static GameObjectCollection gameObjects  = new GameObjectCollection(); //instantiate collection of objects 
 		
   public void init(){
	   //add details later
   }
   
   public void proxy(){
     	//create new instance of proxy 
    	this.setChanged();
        GameWorldProxy proxy = new GameWorldProxy(this);
        this.notifyObservers(proxy);
   }
   
   //get sound 
   public static boolean getIsPlaying(){
	   return isPlaying; 
   }
   
   //set sound
   public void setIsPlaying(boolean isPlaying){
	   this.isPlaying = isPlaying; 
   }
   
   //Change play mode to pause and vise versa 
   public void isPlaying(){
	   
	   //First sets getIsPlaying to true 
	   if(getIsPlaying() == false){
		   setIsPlaying(true);
	   }else{setIsPlaying(false);}
	  
	   proxy();
   }
   
   //Change play mode to pause and vise versa 
   public void isPlayingGameOver(){
	   
	   setIsPlaying(false);
	   proxy();
   }
   
   //return collection for MapView 
   public static GameObjectCollection find(){
	    
	   return gameObjects;
   }
   
   //get sound 
   public boolean getSound(){
	   return sound; 
   }
   
   //set sound
   public void setSound(boolean sound){
	   this.sound = sound; 
   }
   
   //change sound to on and off 
   public void changeSoundOnOff(){
	   
	   //Fist sets getSound to true to turn on 
	   //second time sets to false for off.
	   if(getSound() == true){
		   setSound(false);
	   }else{setSound(true);}
	  
	   proxy();
   }
   
   //get clockTime
   public int getClockTime(){
	   return clockTime;

   }
   
   //set Clock time tick
   public void setClockTime(int newClockTime){
	   clockTime = newClockTime;
   }
   
	//get score 
	public int getScore(){
		return score;
    }
	
	//set score 
	public void setScore(int newScore){
		score = newScore;
	}
	
	//get livesleft
	public int getLivesleft(){
		return livesleft;
	}
	
	//set livesleft
	public void setLivesleft(int newLivesLeft){
		livesleft = newLivesLeft;
	}
	
	   
	//get width  
   public static int getMaxWidth(){
		return maxWidth;
	}
   
   //modify height and width of gameworld
   public void setDimentions(int width, int height){
	   this.maxWidth =  width;
	   this.maxHeight = height;
   }
	

	//get width  
   public static int getMaxHeight(){
		return maxHeight;
   }
   
   //about dialog box menu bar 
   public void about() {
		Dialog.show("CSC133- Dr.Doan Nguyen", "Ehsan Hosseinzadeh Khaligh \n"
				    + "California State University, Sacramento\n" + "Version 2\n", "Ok", null);
	}
   
	//make dispbale refuelbutton in refuelCommand class 
	public void RefuelBtnDisablePauseMode(){
		Game.refuel_btn().setEnabled(false);
	}
	
	//make dispbale refuelbutton in refuelCommand class 
	public void RefuelBtnEnablePauseMode(){
		Game.refuel_btn().setEnabled(true);
	}
	
	//change Pause ToPlay Button Name
	public void changePauseToPlayButtonName(){
	    Game.pause_btn().setText("Play");
	    setSound(true);
	    //notify proxy
	    proxy();
	}
	
	//change play to pause Button Name
	public void changePlayToPauseButtonName(){
	    Game.pause_btn().setText("Pause");
	    setSound(false);
	    //notify proxy
	    proxy();
	}
	
	
   //find a station from Arraylist
   public boolean findStation(){
    	
    	boolean haveOneStation = false;
 	    
    	IIterator i = gameObjects.getIterator();
 		while (i.hasNext())
 			if (i.getNext() instanceof SpaceStation){
 				return true;
 			}
 		return haveOneStation;
    }
   
   //find a ship from Arraylist
   public boolean findShip(){
    	
    	boolean haveOneShip= false;

	    IIterator i = gameObjects.getIterator();
		while (i.hasNext())
			if (i.getNext() instanceof Ships){
				return true;
			}
		return haveOneShip;
    }
   
   //find a asteroid from Arraylist
   public boolean findAsteroid(){
    	
    	boolean haveOneSAsteroid = false;

	    IIterator i = gameObjects.getIterator();
		while (i.hasNext())
			if (i.getNext() instanceof Asteroids){
				return true;
			}
		return haveOneSAsteroid;   
    }
   
   //find two asteroids for command x
   public boolean findTwoAsteroid(){
    	
    	boolean haveTwoAsteroids = false;
        int count = 0; //keep track # asteroids we found in list to return true when it is true  
    
  	    IIterator i = gameObjects.getIterator();
  		while (i.hasNext())
  			if (i.getNext() instanceof Asteroids){
  				count++;
  				if(count >= 2){
    				haveTwoAsteroids = true;
    			}
  			}
  		return haveTwoAsteroids; 
    }  
   
   
 //find a missiles from Arraylist
   public boolean findMissile(){
    	
       boolean findMissile = false;
 	   IIterator i = gameObjects.getIterator();
 		while (i.hasNext())
 			if (i.getNext() instanceof Missiles){
 				return true;
 			}
 		return findMissile; 
    }

	//Returns the spot of a ship
	public GameObject ShipSpot(){
		IIterator i = gameObjects.getIterator();
		GameObject ship;
		while (i.hasNext()){
			ship = (GameObject) i.getNext();
			if (ship instanceof Ships)
				return ship;
		}
		return null;
	}
	
	//Returns the spot of an asteroid
	public GameObject AsteroidSpot(){
		IIterator i = gameObjects.getIterator();
		GameObject asteroid;
		while (i.hasNext()){
			asteroid = (GameObject) i.getNext();
			if (asteroid instanceof Asteroids)
				return asteroid;
		}
		return null;
	}
	
	//Returns the spot of a missile
	public GameObject MissileSpot(){ 
		IIterator i = gameObjects.getIterator();
		GameObject missile;
		while (i.hasNext()){
			missile = (GameObject) i.getNext();
			if (missile instanceof Missiles)
				return missile;
		}
		return null;
	}
	
	//Returns the spot of a Space Station 
	public GameObject SpaceStationSpot(){
		IIterator i = gameObjects.getIterator();
		GameObject spaceStation;
		while (i.hasNext()){
			spaceStation = (GameObject) i.getNext();
			if (spaceStation instanceof SpaceStation)
				return spaceStation;
		}
		return null;
	}
	
   //‘a’ – add a new asteroid to the world.
   public void addNewAstroid(){
	 
	  //if its in pause mode do nothing 	
	  if(getIsPlaying()==false){
	      System.out.println("No Action in Pause mode addNewAstroid.");
		  return;
	  } 
	   
	  Asteroids addAstroid = new Asteroids();
	  
      //add to collection		  
	  gameObjects.add(addAstroid);
	  System.out.println("Added new asteroid to the world.");
	  System.out.println(addAstroid.toString());
      //proxy
	  System.out.println("------------" + "\nCreated asteroid in the collection.");
	  proxy();
   }
   
   //‘b’ – add a new blinking space station to the world.
   public void addNewBlinkingSpaceStation(){

	//if its in pause mode do nothing 	
	if(getIsPlaying()==false){
			System.out.println("No Action in Pause mode addNewBlinkingSpaceStation.");
			return;
	 }  
	   
	  //check if we already have a station or not 
	  if(findStation() == false) {
		  SpaceStation addBlinkingStation = new SpaceStation();
          //add to collection		  
		  gameObjects.add(addBlinkingStation);
		  System.out.println("Added new blinking space station to the world.");
		  System.out.println(addBlinkingStation.toString());
	      //proxy
		  System.out.println("------------" + "\nCreated station in the collection.");
		  proxy();
	 
	   }else{
		  System.out.println("Currently there is a blinking space station in the world."); 
	  }
	  
   }
   
   //‘s’ – add a new ship to the world.
   public void addNewShip(){   
	   
		//if its in pause mode do nothing 	
		if(getIsPlaying()==false){
		      System.out.println("No Action in Pause mode addNewShip.");
			  return;
		}
		
	   //check if we already don't have a ship.    
	   if(findShip() == false){

		   gameObjects.add(addship);
		   
		   System.out.println("Added new ship to the world.");
		   addship.setMissleCount(10);//set number of missiles to 10. 
		   System.out.println(addship.toString());
		   //create new instance of proxy 
		   System.out.println("------------" + "\nCreated ship in the collection.");
		   proxy();

	    }else{
	       System.out.println("Currently there is a ship in the world."); 
	    }
		 
   }
   
   //‘i’ – increase the speed of the ship by a small amount.
   public void increaseShipSpeed(){
	   
		//if its in pause mode do nothing 	
		if(getIsPlaying()==false){
		      System.out.println("No Action in Pause mode increaseShipSpeed.");
			  return;
		}
	   
	   //check if we already have a ship. 
	   if(findShip() == true){   
	    
		   if(addship.getSpeed() < 10 & addship.getSpeed() >= 0){
		     addship.SpeedIncrease();
		     System.out.println("Ship speed incresed by one.");
		     System.out.println(addship.toString());
		     //proxy
		     System.out.println("------------" + "\nShip speed incresed in the collection.");
		     proxy();
		  	 
	       }
	        else{
		     System.out.println("speed should be in range 0 to 10.");
	       }
		
	   }else{
		   //if we don't have a ship.
		   System.out.println("Create a ship in the world first.");
	   }
	   
	   
   }
   
   /*‘d’ – decrease the speed of the ship by a small amount, as long as doing so doesn’t make
   the speed go negative (the ship can’t go backwards).*/
   public void decreaseShipSpeed(){
	  
	   
		//if its in pause mode do nothing 	
		if(getIsPlaying()==false){
		      System.out.println("No Action in Pause mode decreaseShipSpeed.");
			  return;
		}
		
	   //check if we already have a ship. 
	   if(findShip() == true){   
		      
	      if(addship.getSpeed() <= 10 & addship.getSpeed() > 0){
		     addship.SpeedDecrease();
		     System.out.println("Ship speed decreased by one."); 
		     System.out.println(addship.toString()); 
		     //proxy
		     System.out.println("------------" + "\nShip speed decreased in the collection.");
		     proxy();
	      }
	      else{
		     System.out.println("speed should be in range 0 to 10.");
	      }
	   }else{
		   //if we don't have a ship.
		   System.out.println("Create a ship in the world first.");
	   }
	   
   }

   /*‘l’ (tell) – turn left by a small amount (change the heading of the ship by a small amount in the
    counter-clockwise direction). Note that determination of the actual new heading is not
    the responsibly*/
   public void shipTurnLeft(){
	
	//if its in pause mode do nothing 	
	if(getIsPlaying()==false){
		 System.out.println("No Action in Pause mode shipTurnLeft.");
		 return;
	}   
	   
	//check if we already have a ship. 
	if(findShip() == true){   
		
	    if(addship.getDirection() > 0 & addship.getDirection() <= 360){
	       addship.turnLeft();
	       System.out.println("Ship turned left 10 degrees.");
	       System.out.println(addship.toString());
		   //proxy
		   System.out.println("------------" + "\nShip turn left in the collection.");
		   proxy();
	    }
	    else{
		    System.out.println("Direction should be in range 0 to 360 degrees. Turn right.");
	    } 
	 }else{
		   System.out.println("Create a ship in the world first."); //if we don't have a ship.
	   }
	   
   }
   
   /*r’ – turn right (change the ship heading by a small amount in the clockwise direction).*/
   public void shipTurnRight(){
	 
	//if its in pause mode do nothing 	
	if(getIsPlaying()==false){
			 System.out.println("No Action in Pause mode shipTurnRight.");
			 return;
	 }    
	
	//check if we already have a ship.    
	if(findShip() == true){   
		
	    if(addship.getDirection() >= 0 & addship.getDirection() < 360){
	    		System.out.println("Ship turned right 10 degrees.");
	    		addship.turnRight();
		    	System.out.println(addship.toString());
				
		    	//proxy
		    	System.out.println("------------" + "\nShip turn right in the collection.");
		    	proxy();
	    }
	    else{
	    		System.out.println("Direction should be in range 0 to 360 degrees. Turn left."); 
	     }
	   
     }else{
	    System.out.println("Create a ship in the world first."); //if we don't have a ship.
     }
	
   }
   
   /*‘f’ – fire a missile out the front of the ship. If the ship has no more missiles, an error message
   should be printed; otherwise, add to t*/
   public void fireMissileShip(){
	   
	//if its in pause mode do nothing 	
	 if(getIsPlaying()==false){
				 System.out.println("No Action in Pause mode fireMissileShip.");
				 return;
	 }  
	   
	 //check if we already have a ship.    
	   if(findShip() == true){
	      
		  Missiles addMissiles = new Missiles( (float) addship.getLocation_x(), (float) addship.getLocation_y(), addship.getDirection());
		  if(addship.getMissleCount() == 0){
	    
		        System.out.println("ship has no more missiles.");
	      }
	      else{
			    Sound missileSound = new Sound("MISSLE.WAV");
			    missileSound.play();
	    	  
	            //Update the location of missile where it is fired based on location of ship 
	    	    addMissiles.setLocation_x(addship.getLocation_x());
	    	    addMissiles.setLocation_y(addship.getLocation_y());
	    	    addMissiles.SetFuel(100);
	    	    
	    	    //add to collection	
			    gameObjects.add(addMissiles);
		   
		        int missCount = addship.getMissleCount();
		        addship.setMissleCount(missCount-1); //decrement number of missiles by one. 
		        System.out.println("Fired a Missile.");
		        
		        addMissiles.setDirection(addship.getDirection());
		        addMissiles.setSpeed(addship.getSpeed()+1);
		        System.out.println(addMissiles.toString());
		        System.out.println("Number of Missile's left: " + addship.getMissleCount());
		    	
		        //create new instance of proxy 
		        System.out.println("------------" + "\nFired Missiles in the collection.");
		        proxy();
	     }
		   
	   }else{
		    System.out.println("Create a ship in the world first."); //if we don't have a ship.
	     }
   }
   //get missile count for IGameworld 
   public int getMissleCount1(){
	   return addship.getMissleCount();
   }
   
   //refuel missile in pause mode 
   public void MissileRefuelPauseMode(){
   	
    	IIterator itr1 = gameObjects.getIterator();
    	Object currentObject = new Object();
    	while (itr1.hasNext()){
		    	currentObject = itr1.getNext();
		    	if ((currentObject instanceof Missiles)){
		    	  if(((Missiles) MissileSpot()).getContainFlag()==false){
		    		   ((Missiles) MissileSpot()).SetFuel(100);
		    	  }
		        }
     	}
   }
   
   /*‘j’ – jump through hyperspace. This command causes the ship to instantly jump to the initial
    default position in the middle of the screen, regardless of its current position. (This
    makes it easy to regain control of the ship after it has left visible space; later we will see
    a different mechanism for viewing the ship when it is outside screen space.)*/
    public void jumpThrougHyperspace(){
   
   //if its in pause mode do nothing 	
   if(getIsPlaying()==false){
    	System.out.println("No Action in Pause mode jumpThrougHyperspace.");
    	return;
    }  	
    	
    //check if we already have a ship.	
     if(findShip() == true){
    	  float hyperspace_origin_x = getMaxWidth()/2;//ship initial x location 
    	  float hyperspace_origin_y = getMaxHeight()/2;//ship initial y location 
    	  addship.setLocation_x(hyperspace_origin_x);
    	  addship.setLocation_y(hyperspace_origin_y);
    	  System.out.println("ship jumps to the initial default position.");
    	  System.out.println(addship.toString());
    	  
	    //proxy
	     System.out.println("------------" + "\nShip jump Throug Hyper space in the collection.");
	     proxy();
    	  
     }else{
		  System.out.println("Create a ship in the world first."); //if we don't have a ship.
	     }
    	
    }
   
   /*‘n’ – load a new supply of missiles into the ship. This increases the ship’s missile supply to
    the maximum. It is permissible to reload missiles before all missiles have been fired,
	but it is not allowed for a ship to carry more than the maximum number of missiles.
	Note that normally the ship would actually have to fly to a space station to reload; for
	this version of the game we will assume the station has transporters that can transfer
	new missiles to the ship regardless of its location.*/
    public void loadNewMissilesIntoTheShip(){
	   
     //check if we already have a ship.
     if(findShip() == true){	
    	  if(addship.getMissleCount() >=0 & addship.getMissleCount() < 10){
    		  addship.setMissleCount(addship.getMissileCountMax());
    		  System.out.println("missiles loaded to max 10.");
    		  System.out.println("number of missiles: " + addship.getMissleCount());
		      
    		  //create new instance of proxy 
		      System.out.println("------------" + "\nShip's missile number is 10 now in the collection.");
		      proxy();
		   
    	  }
    	  else{
    		  System.out.println("ship has 10 missiles. It cannot have more than 10 missiles.");
    	  }
      }else{
    	  	  System.out.println("Create a ship in the world first."); //if we don't have a ship.
     }
    
    }

    /*‘k’ – a missile has struck and killed an asteroid; tell the game world to remove a missile and
    an asteroid and to increment the player’s score by some amount of your choosing. You
    may choose any missile and asteroid to be removed; later we’ll worry about missiles
    needing to be “close to” their victims. You may assume that this command will not be
    invoked unless there is at least one missile and one asteroid in the world.*/
    public void missileKilledAstroid(){
    	
      if(findMissile() == true && findAsteroid() == true){	
    	
    	  System.out.println("A missile has struck and killed an astroid.");
    	
    	  int currentSocre = getScore();//get score
    	  setScore(currentSocre+1); //increment users score 
    	  System.out.println("Score: " + getScore());
       
    	  //create new instance of proxy 
	      System.out.println("------------" + "\nMissile killed astroid in the collection.");
	      proxy();

     	  Sound missileStruckedAsteroid = new Sound("missileStruckedAsteroid.wav");
     	  missileStruckedAsteroid.play();
		  gameObjects.remove(AsteroidSpot());
		  gameObjects.remove(MissileSpot());
		  
		  
  
      }else{
	    	  System.out.println("fire a missile or create an asteroid in the world first."); //if we don't have a ship.
      }

    }
    
    public void ScoreIncrement(){
  	  int currentSocre = getScore();//get score
  	  setScore(currentSocre+1); //increment users score 
  	  System.out.println("Score: " + getScore());
    }
    
    /*‘c’ – the ship has crashed into an asteroid; tell the game world to remove the ship and an
    asteroid and to decrement the count of lives left; if no lives are left then the game is
    over. You may choose any asteroid to be removed; later we’ll worry about asteroids
    needing to be “close to” the ship. You may assume that this command will not be
    invoked unless there is at least one ship and one asteroid in the world.*/
    public void shipCrashedIntoAstroid(){
    	
    if(findAsteroid() == true && findShip() == true){	
    	
    	int decrementLivesLeft = getLivesleft();
    	if(getLivesleft()>0){
    			setLivesleft(decrementLivesLeft - 1);
    			System.out.println("Lives left: "+ getLivesleft());
    			System.out.println("The ship has crashed into an astroid.");
    	}else{
    		    setLivesleft(4);
    	}

	    
	     if(decrementLivesLeft > 0){
	    	 
			gameObjects.remove(ShipSpot());
			gameObjects.remove(AsteroidSpot());
			
	    	//create new instance of proxy 
		    System.out.println("------------" + "\nship Crashed Into Astroid in the collection.");
		    proxy();
			
	     }if(getLivesleft() == 0){
	    	 System.out.println("No Lives are Left. Game Over!");
	    	 Sound gameOverSound = new Sound("GameOver.wav");
	    	 System.out.println("New Game Has started.");
	    	 gameOverSound.play();
	     }
       
     }else{System.out.println("create a ship or an asteroid in the world first."); }
    }//end method
    
    public void GameOverDilogBox(){
    	Dialog.show("No Lives are Left. Game Over!", "Score: "+Integer.toString(getScore()), "Reset Game", null);
    }
    
    //Remove all gameObjects after GameOver 
    public void resetGame(){
        
	    IIterator iterator = gameObjects.getIterator();
		GameObject currentGameObj;
		while (iterator.hasNext()){
			currentGameObj = (GameObject) iterator.getNext();
			System.out.println(currentGameObj);
			gameObjects.removeAll();
		}
    }
    
    public void decrementLives(){
    	
    	int decrementLivesLeft = getLivesleft();
    	if(getLivesleft()>0){
    			setLivesleft(decrementLivesLeft - 1);
    			System.out.println("Lives left: "+ getLivesleft());
    	}else{
    		    setLivesleft(3);
    	}
	    
	     if(decrementLivesLeft > 0){
	    	 
	    	//create new instance of proxy 
	    	 proxy();

	     }if(getLivesleft() == 0){
	    	 System.out.println("No Lives are Left. Game Over!");
	    	 Sound gameOverSound = new Sound("GameOver.wav");
	    	 gameOverSound.play();
	    	 System.out.println("New Game Has Started.");
	    	 //change the status of game in pasue mode after reset
	    	 setClockTime(0);
	    	 setScore(0);
	    	 addship.setMissleCount(0);
	    	 GameOverDilogBox();
	    	 setLivesleft(3);//set new Livesleft
	    	 resetGame();//Remove all game objects 
	     }
    }

    
    /*‘x’ -- two asteroids have collided with and exterminated each other; tell the game world to
    remove two asteroids from the game. You may choose any two asteroids to be
    removed; later we’ll worry about the asteroids needing to be “close to” each other. You 
    may assume that this command will not be invoked unless there are at least two
    asteroids in the world.*/
    public void astroidsHaveCollidedEachOther(){
    	
     if(findTwoAsteroid()==true){
    		    	
			System.out.println("Two asteroids have collided with exterminating each other.");
			gameObjects.remove(AsteroidSpot());
			gameObjects.remove(AsteroidSpot());
			
	    	//create new instance of proxy 
		    System.out.println("------------" + "\nastroids Have Collided Each Other in the collection.");
		    proxy();
    		   
    	}else{
  	  	  System.out.println("You should have at least two asteroids in the world."); 
        }
    }

    /*‘t’ – tell the game world that the “game clock” has ticked. Each tick of the game clock has
    the following effects: (1) all movable objects are told to update their positions
    according to their current direction and speed; (2) every missile’s fuel level is reduced
    by one and any missiles which are now out of fuel are removed from the game; (3) each
    space station toggles its blinking light if the tick count modulo the station’s blink rate is
    zero; and (4) the “elapsed game time” is incremented by one.*/
	public void AsteroidStationCollideteroids(){
		while(!AsteroidCollide.isEmpty()){
			gameObjects.remove((GameObject)AsteroidCollide.firstElement());
			AsteroidCollide.remove(0);
		}
	}

	public void MissileRemoveissile(){
		while(!MissileCollide.isEmpty()){
			gameObjects.remove((GameObject)MissileCollide.firstElement());
			MissileCollide.remove(0);
			score+=1;
		}
	}
	
	public void AsteroidCollidehip(){
		while(!ShipCollide.isEmpty()){
			gameObjects.remove((GameObject)ShipCollide.firstElement());
			ShipCollide.remove(0);
		}
		decrementLives();
		
		if(getLivesleft()>0){
		   addNewShip();
		}
	}
    
    public void gameClockHasTicked(){
    	
    	int count = 0;
    	int clocktime_increment = getClockTime()+1;//get clockTime()
    	setClockTime(clocktime_increment);//increment clockTime by one 
    	//System.out.println("elapsed time: " + clocktime_increment);
    	
    	IIterator itr1 = gameObjects.getIterator();
		IIterator itr2 = gameObjects.getIterator();
		Object currentObject = new Object();
		
		if (itr1.hasNext() == false){
			//System.out.println("Game world is empty.");
		}else{
			while (itr1.hasNext()){
				currentObject = itr1.getNext();
				if (currentObject instanceof IMovable){
					IMovable mObj = (IMovable) currentObject;
					mObj.move();
					count++;
      			   
					if(count == 1){
	      			    //Print this massage only once when objects move
	      			    //System.out.println("Objects updated their positions.");
	      			    }
				}
			}
    	
			while (itr2.hasNext()){
				currentObject = itr2.getNext();
				if (currentObject instanceof Missiles){
					if(((Missiles) MissileSpot()).getFuelLevel() > 0){
	    				
    		     		int decrementFuelLevel = ((Missiles) MissileSpot()).getFuelLevel();
    		     		((Missiles) MissileSpot()).SetFuel(decrementFuelLevel-1);
    		     		proxy();
    					//System.out.println("Fuel level: "+ addMissiles.getFuelLevel() + "\n");
    			
    		       }else if(((Missiles) MissileSpot()).getFuelLevel() == 0){
					    Missiles m = (Missiles) currentObject;
					    gameObjects.removeEnd(m);
					    break;
    				    //System.out.println("Misssile's with zero fuel removed.");
    			}	
					
				}
			}
    	  }		
    	
    	if(findStation()==true){
    		if(getClockTime() % ((SpaceStation) SpaceStationSpot()).getBlinkRate() == 0){
    			((SpaceStation) SpaceStationSpot()).Blink();
    	    }
    	}
    	
    	//collision 
    	IIterator iter = gameObjects.getIterator();
		while(iter.hasNext()){
			ICollider current = (ICollider)iter.getNext();
			IIterator iter2 = gameObjects.getIterator();
			
			
			while(iter2.hasNext()){
				ICollider other = (ICollider) iter2.getNext();
				
				if(current instanceof Asteroids && other instanceof Asteroids && current!=other){
					if(current.collidesWith(other))
						System.out.println("COLLISION!!!");
					
					if(current.collidesWith(other) && !(AsteroidCollide.contains(current) && AsteroidCollide.contains(other))){
						System.out.println(current);
						System.out.println(other);
						AsteroidCollide.add((GameObject)current);
						AsteroidCollide.add((GameObject)other);
						System.out.println("Two Ateroids have collided.");
				        Sound missileStruckedAsteroid = new Sound("asteroidCrashAsteroid.WAV");
				     	missileStruckedAsteroid.play();
						AsteroidRemove = true;
					}
				}else if((current instanceof Asteroids && other instanceof Ships || other instanceof Ships && current instanceof Asteroids)
						&& !(ShipCollide.contains(current) && AsteroidCollide.contains(other))){
					if(current.collidesWith(other)){
						if(current instanceof Ships){
							ShipCollide.add((GameObject)current);
							AsteroidCollide.add((GameObject)other);
						}else{
							ShipCollide.add((GameObject)other);
							AsteroidCollide.add((GameObject)current);
						}
						System.out.println("Ships and Asteroid have collided");
						AsteroidRemove = true;
						shipRemove = true;
					}
				}else if((current instanceof Missiles && other instanceof Asteroids) || (current instanceof Asteroids && other instanceof Missiles) 
						&& !(AsteroidCollide.contains(current) && AsteroidCollide.contains(other))){
					if(current.collidesWith(other)){
				     	 Sound missileStruckedAsteroid = new Sound("missileStruckedAsteroid.wav");
				     	 missileStruckedAsteroid.play();
						if(current instanceof Missiles){
							MissileCollide.add((GameObject)current);
							AsteroidCollide.add((GameObject)other);
							System.out.println("collision");
						}else{
							MissileCollide.add((GameObject)other);
							AsteroidCollide.add((GameObject)current);
							System.out.println("collision");
						}
       
						System.out.println("Missiles and Asteroid have collided");
						AsteroidRemove = true;
						MissileRemove = true;
					}
				}else if((current instanceof SpaceStation && other instanceof Ships) || (current instanceof Ships && other instanceof SpaceStation)){
					if(current.collidesWith(other)){
						System.out.println("Ships and SpaceStation have collided");
						
						if(current instanceof Ships){
							
							addship.setMissleCount(10);
						}else{
							
							addship.setMissleCount(10);
						}
					}
				}
			}
		}
	
	if (AsteroidRemove){ 
		AsteroidStationCollideteroids(); 
	    AsteroidRemove = false;
	}
	if (MissileRemove){
		MissileRemoveissile();
		MissileRemove = false;
	}
	if (shipRemove){
		AsteroidCollidehip();
		shipRemove = false;
	}
	
	 /*IIterator iter1 =  gameObjects.getIterator();
	    while(iter1.hasNext()){
	    
	    		ICollider curObj = (ICollider)iter1.getNext(); // get a collidable object
	    		// check if this object collides with any OTHER object
	    		IIterator iter2 =  gameObjects.getIterator();
	    		
	    		while(iter2.hasNext()){
	    			
	    			ICollider otherObj = (ICollider) iter2.getNext(); // get a collidable object
	   
	    		  	if(((curObj instanceof Ships)&&(otherObj instanceof SpaceStation))||((otherObj instanceof Ships)&&(curObj instanceof SpaceStation))){
	    		  		// check for collision
	    		  		if(otherObj!=curObj){ 
	    		  			if(curObj.collidesWith(otherObj)){
	    		  				System.out.println("Ships and SpaceStation have collided");
	    		  				addship.setMissleCount(10);
	    		  			}
	    		  		}
	    		  		
	    		  	}
	    		}
	    }*/
    	
    	//create new instance of proxy 
	    proxy();

    }
    
    /*‘p’ – print a display (output lines of text) giving the current game state values, including: (1)
    current score (number of points the player has earned), (2) number of missiles currently
    in the ship, and (3) current elapsed time. Output should be well labeled in easy-to-read
    format.*/
    public void printGameStatusValues(){
    	System.out.println("Current Score: " + getScore());
    	System.out.println("Current elapsed time: " + getClockTime());
    	System.out.println("Number of missiles currently in ship: "+ addship.getMissleCount());
    }
    
    /*‘m’ – print a “map” showing the current world state (see below).*/
    public void printMapCurrentWorldState(){
    	
		//iterator for the collection
		IIterator itr = gameObjects.getIterator();
		Object currentObject = new Object();
		
		if (itr.hasNext() == false)
			System.out.println("We don't have any game object in the collection.");
		else{
		      while(itr.hasNext() ){
		      	currentObject = itr.getNext();
		        System.out.println(currentObject.toString() );
		      }
		}
		
    }
    
    /*‘q’– quit, by calling the method System.exit(0) to terminate the program. Your program
    should first confirm the user’s intent to quit before actually exiting.*/
    public void quit(){
    	Boolean ok_btn = Dialog.show("Confirm quit", "Do you want to quit?", "Yes", "No");
    	if (ok_btn){
    		System.exit(0);
    	}
    }
}
