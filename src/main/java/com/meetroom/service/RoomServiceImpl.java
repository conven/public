package com.meetroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetroom.model.Room;
import com.meetroom.repository.RoomRepository;


@Service("roomService")
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public List<Room> getAllToDo() {
		return roomRepository.findAll();
	}
}
