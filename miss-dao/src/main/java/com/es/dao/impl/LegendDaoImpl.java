package com.es.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.es.dao.face.LegendDaoFace;
import com.es.entity.Legend;
/**
* Legend Dao Impl 
*/ 
@Component("legendDao")
public class LegendDaoImpl extends BaseDao implements LegendDaoFace{
	private static final Logger log = LoggerFactory.getLogger(LegendDaoImpl.class);
	@Override
	public int save(Legend bean){
		try{
			getSession().save(bean);
		}catch(Exception e){
			return 0;
		}
		return 1;
	}

	@Override
	public int delete(Long id){
		try{
			Legend bean = selectById(id);
			if(bean!=null){
				getSession().delete(bean);
			}
		}catch(Exception e){
			return 0;
		}
		return 1;
	}

	@Override
	public int update(Legend bean){
		try{
			getSession().update(bean);
		}catch(Exception e){
			return 0;
		}
		return 1;
	}

	@Override
	public Legend selectById(Long id){
		Session session = getSession();
		Legend instance = (Legend) session.get("com.es.entity.Legend", id);
		session.clear();
		return instance;
	}

	@Override
	public List<Legend> lists(Integer num,Integer size) {
		String hql = "from Legend";
		Query query = getSession().createQuery(hql);
		if(num!=0 || size!=0){
			query.setFirstResult(size*(num-1));
			query.setMaxResults(size);
		}
		List list = query.list();
		if(list!=null && list.size()>0){
			return (List<Legend>)list;
		}else{
			return new ArrayList<Legend>();
		}
	}

}

