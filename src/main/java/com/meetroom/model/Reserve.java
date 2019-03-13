package com.meetroom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reserve {
	
	@Id
	@GeneratedValue
	private long id;	
	private String romId;
	private String usrNm;
	private String rsvPw;
	private String rsvDt;
	private int strCd;
	private int endCd;
	private int rsvTp;	
	
	private boolean completed;

	public Reserve() {
		super();
	}
	
	
/*	public Reserve(long id, String romId, String usrNm, String rsvPw, String rsvDt, int strCd, int endCd, int rsvTp, boolean completed) {
		super();
		this.id = id;
		this.romId = romId;
		this.usrNm = usrNm;
		this.rsvPw = rsvPw;
		this.rsvDt = rsvDt;
		this.strCd = strCd;
		this.endCd = endCd;
		this.rsvTp = rsvTp;
		
		this.completed = completed;
	}*/


	public Reserve(String romId, String usrNm, String rsvPw, String rsvDt, int strCd, int endCd, int rsvTp, boolean completed) {
		super();
		this.romId = romId;
		this.usrNm = usrNm;
		this.rsvPw = rsvPw;
		this.rsvDt = rsvDt;
		this.strCd = strCd;
		this.endCd = endCd;
		this.rsvTp = rsvTp;
		this.completed = completed;
	}
	public long getId() {
		return id;
	}
	
	public String getRomId() {
		return romId;
	}
	public void setRomId(String romId) {
		this.romId = romId;
	}
	
	public String getUsrNm() {
		return usrNm;
	}
	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}
	
	public String getRsvPw() {
		return rsvPw;
	}
	public void setRsvPw(String rsvPw) {
		this.rsvPw = rsvPw;
	}
	
	public String getRsvDt() {
		return rsvDt;
	}
	public void setRsvDt(String rsvDt) {
		this.rsvDt = rsvDt;
	}
	
	public int getStrCd() {
		return strCd;
	}
	public void setStrCd(int strCd) {
		this.strCd = strCd;
	}
	
	public int getEndCd() {
		return endCd;
	}
	public void setEndCd(int endCd) {
		this.endCd = endCd;
	}
	
	public int getRsvTp() {
		return rsvTp;
	}
	public void setRsvTp(int rsvTp) {
		this.rsvTp = rsvTp;
	}
	public boolean getCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
	
	

}
