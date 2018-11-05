package com.es.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
* user entity. 
*/ 
@Entity
@Table(name = "user", schema = "xsyw")
@SuppressWarnings("serial")
public class User implements java.io.Serializable {
	/**
	* Fields 
	*/ 
	private Long id;
	private String name;
	private String pass;
	/**
	* Constructor 
	*/ 
	public User(){ 
		super();
	}
	/**
	* Get & Set 
	*/ 
	public void setId(Long id){
		this.id=id;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	@Column(name="NAME")
	public String getName(){
		return name;
	}
	@Column(name="PASS")
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}

