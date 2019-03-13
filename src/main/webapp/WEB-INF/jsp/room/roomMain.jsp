<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<style>
body {background:#ffc700; font-family: 'NotoSans Regular','Malgun Gothic','맑은 고딕','Apple SD Gothic Neo','돋움',dotum, sans-serif}
.header table {border:1px solid black;}
.header table th{width:100px; height:50px; background:#331a00; color:white; font-size:14px; font-weight: bold; }
.header table td input {font-size:20px; font-weight:bold; height:45px;}  
.header table td{width:100px; border:1px solid black; font-size: 12px; height:40px;} 

.contents table {border:1px solid black;}
.contents table th{width:100px; height:50px; background: #331a00; color:white; font-size:14px; font-weight: bold; }  
.contents table td{width:140px;  font-size: 12px; height:40px;}
.contents table td input {font-size:14px; font-weight:bold; height:45px; }
.contents table td select {font-size:14px; font-weight:bold; height:45px;}    

.contents .table_2 {width:700px;}
.contents .table_2 th {width:150px;}
.contents .table_2 td {width:200px;}

/*
.table_1 {border:1px solid black;} 
.table_1 th{width:80px; border:1px solid black;}
.table_1 td{border:1px solid black; cursor:pointer;}
*/   
</style>
<link rel="stylesheet" href="https://img.dongwonmall.com/dwmall/admin/common/js/jquery-ui-git.css">
<script src="https://img.dongwonmall.com/dwmall/admin/common/js/jquery-1.11.3.js"></script>
<script src="https://img.dongwonmall.com/dwmall/admin/common/js/jquery-ui-git.js"></script>
<script src="https://img.dongwonmall.com/dwmall/admin/common/js/json2.js"></script>



<script>
var sToday = ${nowDt};
var sDateValidation = true;
$(document).ready(function(){
	//$("#nowDt").datepicker();
	$("#nowDt").datepicker({
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin:['일','월','화','수','목','금','토'],
		changeMonth: true,
		changeYear: true,
		showMonthAfterYear:true,
		dateFormat:'yymmdd'
	});
	getSchedule();
	
	$("#nowDt").change(function(){getReserve();});
	$("#btnSave").click(function(){goRsvRoom();});
});

function goRsvRoom(){
	var con = confirm("회의실을 예약하시겠습니까?")
	if(con){		
		var romId = $("#rsvId").val();
		var usrNm = $("#usrNm").val();
		var rsvPw = $("#rsvPw").val();
		var rsvDt = $("#rsvDt").val();
		var strCd = $("#rsvStrCd").val();
		var endCd = $("#rsvEndCd").val();
		var rsvTp = $("#rsvTp").val();
		
		if($.trim(usrNm)==""){alert("예약자명을 입력해주세요.");$("#usrNm").focus();return;}
		if($.trim(rsvPw)==""){alert("예약목적을 입력해주세요.");$("#rsvPw").focus();return;}
		
		//유효성 검증
		var json = new Object();
		json.romId = romId;
		json.usrNm = usrNm;
		json.rsvPw = rsvPw;
		json.rsvDt = rsvDt;
		json.strCd = strCd;
		json.endCd = endCd;
		json.rsvTp = rsvTp;
		json.completed = true;
		
		var data = JSON.stringify(json);
		console.log(data);
	    $.ajax({
	         type : "POST",	        
	         url : "/reserve",
	         contentType: 'application/json',
	         dataType : 'json',
	         data : data,
	         success : function(data){
	        	 var rtnCd = data.rtnCd;
	        	 
	        	 if(data.rtnCd == "00"){
	        		 $("#meetRoomRsv").dialog('close'); 
	        	 }else if(data.rtnCd == "01"){
	        		 alert(data.rsvDt+"일에 해당시간은 이미 예약되었습니다.\n예약 시간을 확인하세요.");
	        	 }else{
	        		 alert("시스템 에러가 발생했습니다.\n\n재시도 후 오류 발생시 IT문의 바랍니다.");
	        	 }
	        	 getReserve();
	        	 
	         },error: function(data){alert("시스템 에러가 발생했습니다.\n재시도 후 오류 발생시 IT문의 바랍니다.");}
	      });
	}
}

function dateValidate(obj){
	cDate = $(obj).val();
	if(cDate < sToday){
		sDateValidation = false;
		alert("지난일로 회의실을 예약할 수 없습니다.");
	}
}


function lyRommRsv(roomId, roomNm, timeId){ 
	$("#rsvId").val(roomNm);
	$("#rsvDt").val($("#nowDt").val());
	$("#usrNm").val("");
	$("#rsvPw").val("");
	$("#rsvTp").val(0);
	$("#rsvStrCd, #rsvEndCd").val(timeId);
	$("#meetRoomRsv").dialog({title:"예약하기", width:730, height:300, modal:true, resizable:true });
	$("#usrNm").focus();
	
}

function setGrid(pTime, pRoom){
	var resultHTML = [];
	 $.each(pRoom, function(index, item) {
		 var roomNm = item.romNm;
		 //타이틀 정보 생성 
		 if(index==0){
			 resultHTML.push('<tr>');
		     resultHTML.push('<th>구분</th>');
			 $.each(pTime, function(index2, item2) {
	     	 	var timeCd = item2.id;
	     	 	var strCd = item2.strCd;
	     	 	var endCd = item2.endCd;
	     	 	resultHTML.push('<th id="'+timeCd+'">'+strCd+'</br>~'+endCd+'</th>');
			 });
			 resultHTML.push('</tr>');
		 }
		 
		 resultHTML.push('<tr>');
	     resultHTML.push('<th>'+item.romNm+'</th>');
		 $.each(pTime, function(index2, item2) {
     	 	var timeCd = item2.id;
     	 	var strCd = item2.strCd;
     	 	var endCd = item2.endCd;
     	 	resultHTML.push('<td id="'+roomNm+'_'+timeCd+'" style="cursor:pointer;" onclick=\lyRommRsv('+item.id+',"'+roomNm+'",'+item2.id+')\></td>');
		 });
		 resultHTML.push('</tr>');
		 
	 });
	 $("#scheduleGrid").append(resultHTML.join(''));
}

function setTimeSelect(pTime){
	var resultHTML = [];
	resultHTML.push('<select id="rsvStrCd">');
	$.each(pTime, function(index, item) {
		var timeCd = item.id;
  	 	var strCd = item.strCd;
  	 	resultHTML.push('<option value="'+timeCd+'">'+item.strCd+'</option>');
	});
	resultHTML.push('</select>');
	$("#rsvStrDt").append(resultHTML.join(''));
	
	var resultHTML2 = [];
	resultHTML2.push('<select id="rsvEndCd">');
	$.each(pTime, function(index, item) {
		var timeCd = item.id;
  	 	var endCd = item.endCd;
  	 	resultHTML2.push('<option value="'+timeCd+'">'+item.endCd+'</option>');
	});
	resultHTML2.push('</select>'); 
	$("#rsvEndDt").append(resultHTML2.join('')); 
}

function gridReserve(rsv){	
	$.each(rsv, function(index, item) {
		//console.log(item.romId+'_'+item.strCd+'_'+item.endCd);
		var tRoomId = item.romId;
		var tUsrNm = item.usrNm;
		var tStrCd = item.strCd*1;
		var tEtrCd = item.endCd*1;
		for(var i = tStrCd; i <= tEtrCd; i++){
			var rsvRoomId = item.romId+'_'+i;
			$("#"+rsvRoomId).text(tUsrNm);
			$("#"+rsvRoomId).css("background","#ffd7b3");
			$("#"+rsvRoomId).css("font-weight","bold");
			$("#"+rsvRoomId).css("text-align","center");
			$("#"+rsvRoomId).css("color","black");
			$("#"+rsvRoomId).css("cursor","no-drop");
		}
	});
}	

function gridClear(){
	$("#scheduleGrid td").css("background","white");
	$("#scheduleGrid td").css("cursor","pointer");
	$("#scheduleGrid td").text("");
}

function getReserve(){
	var today = $("#nowDt").val();
    $.ajax({
        type : "GET",
        async:true,
         url : "/reserve?nowDt="+today,
         data : today,
         dataType : 'json',
         async : false,
         success : function(data){        	 
        	 gridClear();
        	 gridReserve(data);
         },error: function(data){alert("시스템 에러가 발생했습니다./n재시도 후 오류 발생시 IT문의 바랍니다.");}
      });
}

function getRoom(tTime){
    $.ajax({
        type : "GET",
        async:true,
         url : "/room",
         dataType : 'json',
         async : false,
         success : function(data){
        	 setGrid(tTime, data);
        	 getReserve();
         },error: function(data){alert("시스템 에러가 발생했습니다./n재시도 후 오류 발생시 IT문의 바랍니다.");}
      });
}

function getSchedule(){
	$.ajax({
      type : "GET",
       async:true,
       url : "/schedule",
       dataType : 'json',
       async : true,
       success : function(data){
    	   getRoom(data);
    	   setTimeSelect(data);
      },error: function(data){alert("시스템 에러가 발생했습니다./n재시도 후 오류 발생시 IT문의 바랍니다.");}
    });
}
</script> 
</head>
<body>
<div class="header">
	<table class="table_1">
	<tr>
		<th>예약일자</th>
		<td><input type="text" id="nowDt" value="${nowDt}" maxlength="8" autocomplete="off"></td>
	</tr>
	</table>	
</div>
<div class="contents">	
	<table class="table_1">
	<tbody id="scheduleGrid">
	</tbody>
	</table>	
</div>
<div class="contents" id="meetRoomRsv" style="display:none;">
	<table class="table_2">
	<tbody>
		<tr>
			<th>자원명</th>
			<td><input type="text" id="rsvId" readonly="readonly" style="width:200px"/></td>
			<th>예약일자</th>
			<td><input type="text" id="rsvDt" readonly="readonly" style="width:200px"/></td>
		</tr>
		<tr>
			<th>예약자</th>
			<td><input type="text" id="usrNm" maxlength="10" style="width:200px"/></td>
			<th>예약시간</th>
			<td
				<dl>
				<div id="rsvStrDt" style="float:left; margin:2px; width:60px"></div>
				<div id="rsvEndDt" style="float:left; margin:2px; width:60px"></div>
				<div style="float:left; margin:2px; width:70px;">  
					<select id="rsvTp">
						<option value="0">-반복-</option>
						<option value="0">당일</option>						
						<option value="1">2주</option>
						<option value="2">3주</option>
						<option value="3">4주</option>
						<option value="4">5주</option>
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<th>예약목적</th> 
			<td colspan="3"><input type="text" id="rsvPw" maxlength="40" style="width:538px;"/></td>
		</tr>
	</tbody>
	</table>
	<div> 
		<div id="btnSave" style="margin:10px auto; border:1px solid gray; width:100px; height:35px; background:#ffc700; color:white; text-align:center; cursor:pointer; font-weight:bold; padding-top:10px;">저장</div>
	</div>	
</div>
</body>
</html>