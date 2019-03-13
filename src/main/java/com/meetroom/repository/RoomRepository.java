package com.meetroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetroom.model.Room;;

@Repository("roomRepository")
public interface RoomRepository extends JpaRepository<Room, Long>{

}
