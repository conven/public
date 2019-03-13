package com.meetroom.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetroom.controller.RoomController;
import com.meetroom.model.Reserve;
import com.meetroom.repository.ReserveRepository;


@Service("reserveService")
public class ReserveServiceImpl implements ReserveService{
	

	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
	@Autowired
	private ReserveRepository reserveRepository;
	
	@Override
	public List<Reserve> getToDoByDate(String today) {
		return reserveRepository.findReserveByRsvDt(today);
	}

	@Override
	public Map<String, Object> saveToDo(Reserve reserve) throws ParseException{
		
		Map<String, Object> rtnImpl = new HashMap<String, Object>();
		boolean savRst = false;
		String pRomId = reserve.getRomId();
		String pRsvDt = reserve.getRsvDt();
		String pUsrNm = reserve.getUsrNm();
		String pRsvPw = reserve.getRsvPw();
		int pStrCd = reserve.getStrCd();
		int pEndCd = reserve.getEndCd();
		int pRsvTp = reserve.getRsvTp();
		boolean pCompleted = reserve.getCompleted();
		
		String sRsvDt = pRsvDt;
    	int rsvWk = reserve.getRsvTp();
    	for(int i=0; i <= rsvWk; i++){
    		Reserve savRsv = new Reserve();
    		
    		List<Reserve> reserveList = reserveRepository.findReserveByrsvDtAndRomId(sRsvDt, pRomId);
    	    for(Reserve item : reserveList){
    	    	int iStrCd = item.getStrCd();
    	    	int iEndCd = item.getEndCd();
    	    	if((pStrCd > iEndCd)||(pEndCd < iStrCd)){	    		
    	    		savRst = true;

    	    	}else{	    		
    	    		rtnImpl.put("rtnCd", "01");
    	    		rtnImpl.put("rsvDt", sRsvDt);
    	    		savRst = false;
    	    		break;
    	    	}
    	    }
    	    if(reserveList.size() == 0 || savRst){
	    		rtnImpl.put("rtnCd", "00");
	    		savRsv.setRomId(pRomId);
	    		savRsv.setRsvDt(sRsvDt);
	    		savRsv.setUsrNm(pUsrNm);
	    		savRsv.setRsvPw(pRsvPw);
	    		savRsv.setStrCd(pStrCd);
	    		savRsv.setEndCd(pEndCd);
	    		savRsv.setRsvTp(pRsvTp);
	    		savRsv.setCompleted(pCompleted);
	    		reserveRepository.save(savRsv);
	    		sRsvDt = convertDate("yyyyMMdd",sRsvDt, 7);

    	    }
    	}  

	    return rtnImpl; 
	}

	public String convertDate(String dateTp, String orginDt, int changeDt) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(dateTp); //날짜 형식에 따라서 달리 할 수 있다.
		Date originDate = format.parse(orginDt);   		    		
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(originDate);
    	cal.add(Calendar.DATE, changeDt);        //laterCnt 만큼 후의 날짜를 구한다. laterCnt 자리에 -7 을 입력하면 일주일전에 날짜를 구할 수 있다.
    	Date laterDate = new Date();
    	laterDate = cal.getTime();
		return format.format(laterDate).toString();
	}
}
