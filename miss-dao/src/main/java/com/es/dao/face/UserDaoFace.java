package com.es.dao.face;
import com.es.entity.Legend;
import com.es.entity.User;
/**
* User Dao 
*/ 
public interface UserDaoFace{

	User findByName(String name);

	int save(String name, String pass);
}

