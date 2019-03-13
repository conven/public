package com.meetroom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meetroom.model.Room;
import com.meetroom.service.RoomService;

@RestController
public class RoomController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

	@Autowired          
	private RoomService roomService;
	
	@RequestMapping(value="/room", method=RequestMethod.GET)
	public ResponseEntity<List<Room>> getRoom(){
    	List<Room> allRoom = roomService.getAllToDo();
    	logger.info("Returning all room list:"+allRoom);
		return new ResponseEntity<List<Room>>(allRoom, HttpStatus.OK);
	}
}
