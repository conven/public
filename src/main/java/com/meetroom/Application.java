package com.meetroom;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.meetroom.model.Reserve;
import com.meetroom.model.Room;
import com.meetroom.model.Schedule;
import com.meetroom.repository.ReserveRepository;
import com.meetroom.repository.RoomRepository;
import com.meetroom.repository.ScheduleRepository;

@SpringBootApplication
public class Application {
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner roomInit(RoomRepository roomRepository) {
		return (args) -> {
			
			int roomCnt = 10;
			for(int now = 1; now <= roomCnt; now++){
				roomRepository.save(new Room("Room"+now));
			}
		};
	}
	
	@Bean
	public CommandLineRunner scheduleInit(ScheduleRepository scheduleRepository) {
		return (args) -> { 
			int offWork = 17;
			for(int workTime = 9; workTime <= offWork; workTime++){ 
				scheduleRepository.save(new Schedule(workTime+":00",+workTime+":30")); 
				scheduleRepository.save(new Schedule(workTime+":30",+workTime+1+":00"));		
			}
		};
	}
	
	@Bean
	public CommandLineRunner reserveInit(ReserveRepository reserveRepository) {
		return (args) -> {
			reserveRepository.save(new Reserve("Room1","구제근","1234","20190313",1,3,0, false));
			reserveRepository.save(new Reserve("Room2","구제근","1234","20190313",3,5,0, false));
			reserveRepository.save(new Reserve("Room3","김잔디","1234","20190313",2,6,0, false));
			reserveRepository.save(new Reserve("Room4","구민찬","1234","20190313",4,5,0, false));
			reserveRepository.save(new Reserve("Room5","김잔디","1234","20190313",5,8,0, false));
			reserveRepository.save(new Reserve("Room1","구제근","1234","20190314",3,5,0, false));
			reserveRepository.save(new Reserve("Room3","구제근","1234","20190314",1,2,0, false));
			reserveRepository.save(new Reserve("Room4","김잔디","1234","20190314",6,7,0, false));
			reserveRepository.save(new Reserve("Room5","구민찬","1234","20190314",4,5,0, false));
			reserveRepository.save(new Reserve("Room6","김잔디","1234","20190314",2,10,0, false));
			reserveRepository.save(new Reserve("Room7","김잔디","1234","20190314",3,5,0, false));
			reserveRepository.save(new Reserve("Room8","김잔디","1234","20190314",6,10,0, false));
			reserveRepository.save(new Reserve("Room1","구제근","1234","20190315",1,6,0, false));
			reserveRepository.save(new Reserve("Room3","구제근","1234","20190315",2,8,0, false));
			reserveRepository.save(new Reserve("Room4","김잔디","1234","20190315",4,5,0, false));
			reserveRepository.save(new Reserve("Room5","구민찬","1234","20190315",8,9,0, false));
			reserveRepository.save(new Reserve("Room6","김잔디","1234","20190315",1,4,0, false));
			reserveRepository.save(new Reserve("Room7","김잔디","1234","20190315",4,9,0, false));
			reserveRepository.save(new Reserve("Room8","김잔디","1234","20190315",9,10,0, false));
			
			
		};
	}
}

