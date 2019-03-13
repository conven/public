package com.meetroom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {
	
	@Id
	@GeneratedValue
	private long id;
	private String romNm;
	
	public Room() {
		super();
	}
	
	
/*	public Room(long id, String romNm) {
		super();
		this.id = id;
		this.romNm = romNm;
	}*/


	public Room(String romNm) {
		super();
		this.romNm = romNm;
	}
	public long getId() {
		return id;
	}
/*	public void setId(long id) {
		this.id = id;
	}*/
	
	public String getRomNm() {
		return romNm;
	}
/*	public void setRomNm(String romNm) {
		this.romNm = romNm;
	}*/
	

}
