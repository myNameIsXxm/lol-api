package com.es.model;

public class WordBankForShow {
	private String data;
	private String show;
	private int type; // 1:¿‡–Õ 2£∫µ•¥ 
	public WordBankForShow(int type,String data,String show){
		this.data=data;
		this.show=show;
		this.type=type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
