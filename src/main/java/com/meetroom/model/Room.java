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
	

	public Room(String romNm) {
		super();
		this.romNm = romNm;
	}
	public long getId() {
		return id;
	}

	public String getRomNm() {
		return romNm;
	}
}
