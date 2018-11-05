package com.es.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
/**
* Legend entity. 
*/ 
@Entity
@Table(name = "legend", schema = "xsyw")
@SuppressWarnings("serial")
public class Legend implements java.io.Serializable {
	/**
	* Fields 
	*/ 
	private Long id;
	private String name;
	private String camp;
	private String job;
	private String background;
	private String qname;
	private String wname;
	private String ename;
	private String rname;
	private String bdname;
	private String qdesc;
	private String wdesc;
	private String edesc;
	private String rdesc;
	private String bddesc;
	/**
	* Constructor 
	*/ 
	public Legend(){ 
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
	public void setCamp(String camp){
		this.camp=camp;
	}
	@Column(name="CAMP")
	public String getCamp(){
		return camp;
	}
	public void setJob(String job){
		this.job=job;
	}
	@Column(name="JOB")
	public String getJob(){
		return job;
	}
	@Column(name="BACKGROUND")
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	@Column(name="QNAME")
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	@Column(name="WNAME")
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	@Column(name="ENAME")
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	@Column(name="RNAME")
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Column(name="BDNAME")
	public String getBdname() {
		return bdname;
	}
	public void setBdname(String bdname) {
		this.bdname = bdname;
	}
	@Column(name="QDESC")
	public String getQdesc() {
		return qdesc;
	}
	public void setQdesc(String qdesc) {
		this.qdesc = qdesc;
	}
	@Column(name="WDESC")
	public String getWdesc() {
		return wdesc;
	}
	public void setWdesc(String wdesc) {
		this.wdesc = wdesc;
	}
	@Column(name="EDESC")
	public String getEdesc() {
		return edesc;
	}
	public void setEdesc(String edesc) {
		this.edesc = edesc;
	}
	@Column(name="RDESC")
	public String getRdesc() {
		return rdesc;
	}
	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}
	@Column(name="BDDESC")
	public String getBddesc() {
		return bddesc;
	}
	public void setBddesc(String bddesc) {
		this.bddesc = bddesc;
	}
}

