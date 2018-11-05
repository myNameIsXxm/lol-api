package com.es.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BaseDao{
	
	private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);
	
	@Resource(name="hibernateTemplate")
	public HibernateTemplate hibernateTemplate;
	
	@Resource(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate;

	@Resource(name="sessionFactory")
	public SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * ½öÏÞÓÚORACLE
	 * @param tableName
	 * @return
	 */
	public Long getId(String tableName) {
		Session session = getSession();
		String sql = "select af_zjpt_n_getseq(?) from dual";
		Query queryObject = session.createSQLQuery(sql);
		queryObject.setParameter(0, tableName);
		session.clear();
		return Long.valueOf(queryObject.uniqueResult().toString());
	}
	
}
