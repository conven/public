package com.meetroom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Schedule {
	
	@Id
	@GeneratedValue
	private long id;
	private String strCd;
	private String endCd;

	public Schedule() {
		super();
	}
	
	
/*	public Schedule(long id, String strCd, String endCd) {
		super();
		this.id = id;
		this.strCd = strCd;
		this.endCd = endCd;
	}*/


	public Schedule(String strCd, String endCd) {
		super();
		this.strCd = strCd;
		this.endCd = endCd;
	}
	public long getId() {
		return id;
	}
/*	public void setId(long id) {
		this.id = id;
	}*/
	
	public String getStrCd() {
		return strCd;
	}
/*	public void setStrCd(String strCd) {
		this.strCd = strCd;
	}*/
	public String getEndCd() {
		return endCd;
	}
/*	public void setEndCd(String endCd) {
		this.endCd = endCd;
	}*/

}
