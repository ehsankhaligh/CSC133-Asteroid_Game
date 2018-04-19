package com.mycompany.a3.GameObjects;

import com.mycompany.a3.Interfaces.*;
import java.util.ArrayList;
import java.util.Collection;

public class GameObjectCollection implements ICollection{

	private ArrayList<GameObject> collection; 
	
	public GameObjectCollection(){
		collection = new ArrayList<GameObject>();
	}

	//add method to add to collection
    public void add(GameObject newObject) {
	    collection.add(newObject);
	
    }
	
    //remove method to add to the collection 
	public void remove(GameObject newObject) {
		collection.remove(newObject);
	}
	
	//remove all elements in from the ArrayList 
	public void removeAll(){
		collection.clear();
	}
	
    //remove end method to add to the collection 
	public void removeEnd(GameObject newObject) {
		collection.remove(collection.lastIndexOf(newObject));
	}
	
	//get itterator method 
	public IIterator getIterator() {

		return new ObjectCollectionIterator();
	}
	
	//implement iterator as a private inner class
	private class ObjectCollectionIterator implements IIterator
	{
		private int currentElementIndex;
		
		public ObjectCollectionIterator()
		{
			currentElementIndex = -1;
		}

		public boolean hasNext() {
			if(collection.size() <= 0) 
				return false;
			
			if(currentElementIndex == collection.size() - 1)
				return false;
			
			return true;
		}

		public Object getNext() {
			currentElementIndex++;
			return(collection.get(currentElementIndex));
		}
	
	}

}
