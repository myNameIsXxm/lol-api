package com.mysi.service.face;

import com.es.entity.User;

/**
* User Service 
*/ 
public interface UserServiceFace{
	public int save(String name,String pass);

	public User find(String name,String pass);
	
	public User findByName(String name);
}

