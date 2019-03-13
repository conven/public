package com.meetroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetroom.model.Schedule;

@Repository("scheduleRepository")
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

}
