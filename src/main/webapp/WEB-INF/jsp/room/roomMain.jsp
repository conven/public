<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<style>
body {background:#ffc700; font-family: 'NotoSans Regular','Malgun Gothic','���� ���','Apple SD Gothic Neo','����',dotum, sans-serif}
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
		monthNamesShort: ['1��','2��','3��','4��','5��','6��','7��','8��','9��','10��','11��','12��'],
		dayNamesMin:['��','��','ȭ','��','��','��','��'],
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
	var con = confirm("ȸ�ǽ��� �����Ͻðڽ��ϱ�?")
	if(con){		
		var romId = $("#rsvId").val();
		var usrNm = $("#usrNm").val();
		var rsvPw = $("#rsvPw").val();
		var rsvDt = $("#rsvDt").val();
		var strCd = $("#rsvStrCd").val();
		var endCd = $("#rsvEndCd").val();
		var rsvTp = $("#rsvTp").val();
		
		if($.trim(usrNm)==""){alert("�����ڸ��� �Է����ּ���.");$("#usrNm").focus();return;}
		if($.trim(rsvPw)==""){alert("��������� �Է����ּ���.");$("#rsvPw").focus();return;}
		
		//��ȿ�� ����
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
	        		 alert(data.rsvDt+"�Ͽ� �ش�ð��� �̹� ����Ǿ����ϴ�.\n���� �ð��� Ȯ���ϼ���.");
	        	 }else{
	        		 alert("�ý��� ������ �߻��߽��ϴ�.\n\n��õ� �� ���� �߻��� IT���� �ٶ��ϴ�.");
	        	 }
	        	 getReserve();
	        	 
	         },error: function(data){alert("�ý��� ������ �߻��߽��ϴ�.\n��õ� �� ���� �߻��� IT���� �ٶ��ϴ�.");}
	      });
	}
}

function dateValidate(obj){
	cDate = $(obj).val();
	if(cDate < sToday){
		sDateValidation = false;
		alert("�����Ϸ� ȸ�ǽ��� ������ �� �����ϴ�.");
	}
}


function lyRommRsv(roomId, roomNm, timeId){ 
	$("#rsvId").val(roomNm);
	$("#rsvDt").val($("#nowDt").val());
	$("#usrNm").val("");
	$("#rsvPw").val("");
	$("#rsvTp").val(0);
	$("#rsvStrCd, #rsvEndCd").val(timeId);
	$("#meetRoomRsv").dialog({title:"�����ϱ�", width:730, height:300, modal:true, resizable:true });
	$("#usrNm").focus();
	
}

function setGrid(pTime, pRoom){
	var resultHTML = [];
	 $.each(pRoom, function(index, item) {
		 var roomNm = item.romNm;
		 //Ÿ��Ʋ ���� ���� 
		 if(index==0){
			 resultHTML.push('<tr>');
		     resultHTML.push('<th>����</th>');
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
         },error: function(data){alert("�ý��� ������ �߻��߽��ϴ�./n��õ� �� ���� �߻��� IT���� �ٶ��ϴ�.");}
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
         },error: function(data){alert("�ý��� ������ �߻��߽��ϴ�./n��õ� �� ���� �߻��� IT���� �ٶ��ϴ�.");}
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
      },error: function(data){alert("�ý��� ������ �߻��߽��ϴ�./n��õ� �� ���� �߻��� IT���� �ٶ��ϴ�.");}
    });
}
</script> 
</head>
<body>
<div class="header">
	<table class="table_1">
	<tr>
		<th>��������</th>
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
			<th>�ڿ���</th>
			<td><input type="text" id="rsvId" readonly="readonly" style="width:200px"/></td>
			<th>��������</th>
			<td><input type="text" id="rsvDt" readonly="readonly" style="width:200px"/></td>
		</tr>
		<tr>
			<th>������</th>
			<td><input type="text" id="usrNm" maxlength="10" style="width:200px"/></td>
			<th>����ð�</th>
			<td
				<dl>
				<div id="rsvStrDt" style="float:left; margin:2px; width:60px"></div>
				<div id="rsvEndDt" style="float:left; margin:2px; width:60px"></div>
				<div style="float:left; margin:2px; width:70px;">  
					<select id="rsvTp">
						<option value="0">-�ݺ�-</option>
						<option value="0">����</option>						
						<option value="1">2��</option>
						<option value="2">3��</option>
						<option value="3">4��</option>
						<option value="4">5��</option>
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<th>�������</th> 
			<td colspan="3"><input type="text" id="rsvPw" maxlength="40" style="width:538px;"/></td>
		</tr>
	</tbody>
	</table>
	<div> 
		<div id="btnSave" style="margin:10px auto; border:1px solid gray; width:100px; height:35px; background:#ffc700; color:white; text-align:center; cursor:pointer; font-weight:bold; padding-top:10px;">����</div>
	</div>	
</div>
</body>
</html>