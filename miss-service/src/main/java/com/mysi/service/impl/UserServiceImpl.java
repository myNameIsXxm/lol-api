package com.mysi.service.impl;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.es.dao.face.UserDaoFace;
import com.es.entity.User;
import com.mysi.service.face.UserServiceFace;
/**
* User Service Impl 
*/ 
@Component("userService")
@Transactional
public class UserServiceImpl implements UserServiceFace{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Inject
	private UserDaoFace userDao;

	@Override
	public int save(String name, String pass) {
		User user = userDao.findByName(name);
		if(user==null){
			return userDao.save(name,pass);
		}else{
			return 0;
		}
	}

	@Override
	public User find(String name, String pass) {
		User user = userDao.findByName(name);
		if(user!=null&&user.getPass().equals(pass)){
			return user;
		}else{
			return null;
		}
	}

	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}


}

