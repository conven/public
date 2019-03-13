package com.meetroom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meetroom.model.Schedule;
import com.meetroom.service.ScheduleService;

@RestController
public class ScheduleController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public ResponseEntity<List<Schedule>> getRoom(){
    	logger.info("Returning all schedule list");
		return new ResponseEntity<List<Schedule>>(scheduleService.getAllToDo(), HttpStatus.OK);
	}
}
