package com.mycompany.a3.Views;

import com.mycompany.a3.Interfaces.*;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.GameObjects.*;
import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

//map view is observer 
public class MapView extends Container implements Observer {

	private GameWorld gw;
	
	public MapView(){
		this.gw = gw;
	}
	
	public void update(Observable observable, Object arg) {
		
		//prints updated map
		//((IGameWorld)arg).printMapCurrentWorldState();
        //System.out.println("Called update mapView method.");
        
        setWidth(gw.getMaxWidth());
        setHeight(gw.getMaxHeight());
        //System.out.println("MapView MAX_Width: " + this.getWidth());
        //System.out.println("MapView MAX_Height:" + this.getHeight());
		this.repaint();//call repaint by itself
		
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		IIterator itr =  GameWorld.find().getIterator();
		
		 while(itr.hasNext()) {
			 GameObject obj = (GameObject) itr.getNext();
			 ((IDrawable) obj).draw(g, pCmpRelPrnt); 
	
	    }
	}
	
	
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
	 
      //Run if it is in pause mode. 
	  if(GameWorld.getIsPlaying()==false){
		 System.out.println("we are in pause mode and pointerPressed works.");
		 IIterator itr =  GameWorld.find().getIterator();
		 while(itr.hasNext()) {
			 
			 GameObject obj = (GameObject) itr.getNext();
			 			    
			 if (obj instanceof Asteroids){
				 	if (((Asteroids) obj).contains(pPtrRelPrnt, pCmpRelPrnt)){
				 		    ((Asteroids) obj).setSelected(true);
				 		 
				 		  
				 	}else{
				 			((Asteroids) obj).setSelected(false);
				 			
				 	}
			 }	 	
			 if (obj instanceof Missiles){ 	
			        if (((Missiles) obj).contains(pPtrRelPrnt, pCmpRelPrnt)){
			 				((Missiles) obj).setSelected(true);
			 	    }else{
			 				((Missiles) obj).setSelected(false);
			        }
			 }      
			 repaint(); 
	    }//end while 
	  }else{System.out.println("pointerPressed but does not have any action. Actions are in pause mode.");}
		 
	 }//end pointerPressed

}
