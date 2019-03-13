package com.meetroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetroom.model.Reserve;

@Repository("reserveRepository")
public interface ReserveRepository extends JpaRepository<Reserve, Long>{
	
	List<Reserve> findReserveByRsvDt(String rsvDt);
	List<Reserve> findReserveByrsvDtAndRomId(String rsvDt, String romId);

}
