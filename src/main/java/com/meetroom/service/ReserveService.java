package com.meetroom.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import com.meetroom.model.Reserve;

public interface ReserveService {
	public List<Reserve> getToDoByDate(String today);
	public Map<String, Object> saveToDo(Reserve room) throws ParseException;
}

