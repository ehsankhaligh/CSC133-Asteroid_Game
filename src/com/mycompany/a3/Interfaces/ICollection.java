package com.mycompany.a3.Interfaces;

import com.mycompany.a3.GameObjects.*;;

public interface ICollection {
	void add(GameObject newObject);
	void remove(GameObject newObject);
	void removeEnd(GameObject newObject);
	IIterator getIterator();
}
