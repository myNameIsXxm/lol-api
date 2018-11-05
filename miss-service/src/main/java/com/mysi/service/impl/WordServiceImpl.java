package com.mysi.service.impl;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.es.dao.face.WordDaoFace;
import com.es.model.WordBank;
import com.mysi.service.face.WordServiceFace;
@Component("wordService")
@Transactional
public class WordServiceImpl implements WordServiceFace{

	private static final Logger log = LoggerFactory.getLogger(WordServiceImpl.class);

	@Inject
	private WordDaoFace wordDao;

	@Override
	public List<WordBank> findByUser(String user) {
		return wordDao.findByUser(user);
	}

	@Override
	public int delete(String user, String danci) {
		return wordDao.delete(user,danci);
	}

}

