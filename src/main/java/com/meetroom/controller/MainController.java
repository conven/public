package com.meetroom.controller;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meetroom.service.RoomService;

@Controller
public class MainController {
	
	public RoomService roomService;
	
	@RequestMapping(value="/room/roomMain")
	public ModelAndView roomMain(){
		ModelAndView mv = new ModelAndView();
		
		int nowDt = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new java.util.Date()));
		mv.addObject("nowDt", nowDt);
		return mv; 
	}
}
