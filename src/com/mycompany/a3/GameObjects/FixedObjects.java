package com.mycompany.a3.GameObjects;


public abstract class FixedObjects extends GameObject{
	
		private static int idNumber = 1;
	
	/*creating new fixed object having position and color inherit from Game object class*/
	FixedObjects(){
		newIdNumber();
	}
	
	/*overriding move method from IMovable Interface, but fixed  like SpaceStation cannot move*/
	//public void move(){}
	
	public int getIdNumber(){
		return idNumber;
	}
    
	public void setIdnumber(int NewIdNumber){
		idNumber =  NewIdNumber;
	}
	
	public void newIdNumber(){
		idNumber++;
	}
	
	public String toString() {
		
		String parentDesc = super.toString();
		return parentDesc;
	}
    
}
 