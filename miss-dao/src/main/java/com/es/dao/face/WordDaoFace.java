package com.es.dao.face;

import java.util.List;

import com.es.model.WordBank;

public interface WordDaoFace{

	List<WordBank> findByUser(String user);

	int delete(String user, String danci);

}

