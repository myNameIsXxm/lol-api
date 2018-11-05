package com.mysi.service.face;

import java.util.List;

import com.es.model.WordBank;

/**
* User Service 
*/ 
public interface WordServiceFace{

	List<WordBank> findByUser(String user);

	int delete(String user, String danci);
	
}

