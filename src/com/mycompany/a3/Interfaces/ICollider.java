package com.mycompany.a3.Interfaces;

import com.mycompany.a3.*;

public interface ICollider {
    // Apply appropriate response algorithms for detecting and handling collision
	public boolean collidesWith(ICollider other);
}
