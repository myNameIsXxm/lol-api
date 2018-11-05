package com.es.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.es.dao.face.WordDaoFace;
import com.es.model.WordBank;

@Component("wordDao")
public class WordDaoImpl extends BaseDao implements WordDaoFace {
	private static final Logger log = LoggerFactory.getLogger(WordDaoImpl.class);

	@Override
	public List<WordBank> findByUser(String user) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select danci,fanyi,username from word_bank where username=?", new Object[] { user },
				new int[] { java.sql.Types.VARCHAR });
		List<WordBank> result = new ArrayList<WordBank>();
		for (Map<String, Object> map : list) {
			WordBank wb = new WordBank();
			wb.setDanCi(map.get("danci").toString());
			wb.setFanYi(map.get("fanyi").toString());
			wb.setUserName(map.get("username").toString());
			result.add(wb);
		}
		return result;
	}

	@Override
	public int delete(String user, String danci) {
		return jdbcTemplate.update("delete from word_bank where username=? and danci=?", new Object[] { user, danci },
				new int[] { java.sql.Types.VARCHAR, java.sql.Types.VARCHAR });
	}
}
