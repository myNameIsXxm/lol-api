package com.es.dao.impl;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.es.dao.face.UserDaoFace;
import com.es.entity.User;
/**
* User Dao Impl 
*/ 
@Component("userDao")
public class UserDaoImpl extends BaseDao implements UserDaoFace{
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public User findByName(String name) {
		String hql = "from User where name=:name";
		Query query = getSession().createQuery(hql);
		query.setString("name",name);
		List list = query.list();
		if(list!=null && list.size()>0){
			return (User) list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public int save(String name, String pass) {
		User user = new User();
		user.setName(name);
		user.setPass(pass);
		try{
			getSession().save(user);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		//TODO Éú³ÉÒ»Ì×·­Òë
		return 1;
	}

}

