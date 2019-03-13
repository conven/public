package com.meetroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetroom.model.Schedule;
import com.meetroom.repository.ScheduleRepository;


@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Override
	public List<Schedule> getAllToDo() {
		return scheduleRepository.findAll();
	}
}
