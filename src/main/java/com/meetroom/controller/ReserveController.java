package com.meetroom.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meetroom.model.Reserve;
import com.meetroom.service.ReserveService;


@RestController
public class ReserveController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReserveController.class);

	@Autowired
	private ReserveService reserveService;

	@RequestMapping(value="/reserve", method=RequestMethod.GET)
	public ResponseEntity<List<Reserve>> getReserByDate(String nowDt){
    	logger.info("getReserByDate:"+nowDt);
		return new ResponseEntity<List<Reserve>>(reserveService.getToDoByDate(nowDt), HttpStatus.OK);
	}


    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
   	public Map<String, Object> saveToDo(@RequestBody Reserve payload) throws ParseException{
    	logger.info("Payload to save " + payload);
    	Map<String, Object> rtnCtr = new HashMap<String, Object>();
    	rtnCtr = reserveService.saveToDo(payload);
		return rtnCtr;
   	}
}
