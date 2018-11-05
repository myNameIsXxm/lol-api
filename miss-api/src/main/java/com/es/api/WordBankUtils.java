package com.es.api;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.es.dao.impl.BaseSqlConnect;
import com.es.entity.Legend;
import com.es.model.WordBank;

public class WordBankUtils {
	private WordBankUtils() {
	}

	private static List<WordBank> result;

	public static void flushWordBank() {
		Connection conn = BaseSqlConnect.getConnection();
		List<String[]> list = BaseSqlConnect.queryForList("select danci,fanyi,username from word_bank", conn);
		result = new ArrayList<WordBank>();
		for (String[] s : list) {
			WordBank wb = new WordBank();
			wb.setDanCi(s[0]);
			wb.setFanYi(s[1]);
			wb.setUserName(s[2]);
			result.add(wb);
		}
	}
	
	public static List<WordBank> getWordBank() {
		if (result == null) {
			synchronized (WordBankUtils.class) {
				if (result == null) {
					flushWordBank();
				}
			}
		}
		return result;
	}
	
	public static List<WordBank> getWordBankByUser(String user) {
		if (result == null) {
			synchronized (WordBankUtils.class) {
				if (result == null) {
					flushWordBank();
				}
			}
		}
		return filterByUser(result,user);
	}
	
	public static List<WordBank> getWordBankByUserWithSzm(String user) {
		List<WordBank> result = getWordBankByUser(user);
		for(WordBank wb:result){
			wb.setSzm(wb.getDanCi().toUpperCase().charAt(0));
		}
		return result;
	}

	public static List<Legend> addFanYi(List<Legend> lists,String user) {
		for(Legend l:lists){
			addFanYi(l,user);
		}
		return lists;
	}

	public static Legend addFanYi(Legend a,String user) {
		List<WordBank> wbs = getWordBank();
		wbs = filterByUser(wbs,user);
		for(WordBank wb:wbs){
			String d1 = wb.getDanCi();
			String d2 = wb.getDanCi()+"("+wb.getFanYi()+")";
			a.setBackground(a.getBackground().replaceAll(d1,d2));
			a.setBddesc(a.getBddesc().replaceAll(d1,d2));
			a.setBdname(a.getBdname().replaceAll(d1,d2));
			a.setCamp(a.getCamp().replaceAll(d1,d2));
			a.setEdesc(a.getEdesc().replaceAll(d1,d2));
			a.setEname(a.getEname().replaceAll(d1,d2));
			a.setJob(a.getJob().replaceAll(d1,d2));
			a.setName(a.getName().replaceAll(d1,d2));
			a.setQdesc(a.getQdesc().replaceAll(d1,d2));
			a.setQname(a.getQname().replaceAll(d1,d2));
			a.setRdesc(a.getRdesc().replaceAll(d1,d2));
			a.setRname(a.getRname().replaceAll(d1,d2));
			a.setWdesc(a.getWdesc().replaceAll(d1,d2));
			a.setWname(a.getWname().replaceAll(d1,d2));
		}
		return a;
	}

	private static List<WordBank> filterByUser(List<WordBank> wbs, String user) {
		List<WordBank> result = new ArrayList<WordBank>();
		for(WordBank wb:wbs){
			if(wb.getUserName().equals(user)){
				result.add(wb);
			}
		}
		return result;
	}

}
