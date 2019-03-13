# 회의실예약 애플리케이션

회의실과 날짜, 사용 시간을 입력하여 회의실을 예약하는 회의실 예약 애플리케이션 개발.


### 기능 요구사항 정의
  > 필수기능
   - [x] 정시시작-30분단위 예약
   - [x] 주단위 반복예약 기능
   - [x] 같은시간 중첩예약 방지(종료시간과 시작시간은 중복가능)
   - [x] 다중접속자 처리

  > 추가기능
   - [ ] 사용자 관리
   - [x] 회의실 갯수 관리
   - [x] 회의시간 관리(퇴근시간 이후 회의는..)

### 기술 요구사항 정의
  - [x] Web Application(필수)
  - [x] Back End - REST API(필수)
  - [x] Front End(자유)
  - [x] In Memory DB 사용(선택)
  - [x] 단위테스트(필수)
  - [ ] 통합테스트(선택)

### 사용기술 고려
  - 주력 개발스택
    + JSP, JSTL, JAVA(Spring3.1), IBATIS, ORACLE
  - 개발 희망스택
    + Vue or React, JAVA(Spring boot), JPA, H2
  - GAP분석
    + 익숙하지 않은 희망스택으로 퇴근시간과 주말내 완성할 수 있을지 고민
    + 이번 기회에 희망스택 연습하자(망할지라도ㅡ.ㅜ)
    + 서버단은 익숙하지 않더라도 희망 스택!! 프론트는 쓰던걸로 절충!
 
 ### 개발기술 확정
  - Front End : JSP, Jquery 
  - Back End : Java1.8, Spring Boot1.4.2, JPA, H2
  - Dependencies :
    + spring-boot 1.4.2.RELEASE
     * spring-boot-starter-web
     * tomcat-embed-jasper
     * spring-boot-starter-test
     * spring-boot-starter-data-jpa
    + com.h2database:h2
  - 단위테스트:Junit
  - IDE: Eclipse
 
### 설계
 - Model & Repository
   + Room & RoomRepository: 회의실
     * 회의실ID
     * 회의실명
   + Schedule & ScheduleRepository : 사용가능 시간
     * 시간ID     
     * ID의 시작시간
     * ID의 종료시간
   + Reserve & ReserveRespository: 예약내역
     * 회의실ID
     * 시작시간ID
     * 종료시간ID
     * 예약자명
     * 예약내용
 - Controller & ServiceImpl
   + MainController & MainServiceImpl : 메인페이지 관리
   + RoomController & RoomCServiceImpl : 회의실 관리
   + ScheduleController & ScheduleServiceImpl : 사용가능 시간 관리
   + ReserveController & ReserveServiceImpl : 예약 관리
  
### 구현
  - 초기화면
    + 회의실 : 10개
    + 사용가능시간 : 09:00~18:00
  
<img src="https://user-images.githubusercontent.com/31336580/54277383-0a049800-45d3-11e9-8eaa-5146765bdc4b.png" width="90%"></img>

  - 예약화면
    + 자동입력 : 자원명, 예약일자
    + 필수입력 : 예약자, 예약시간, 예약목적
    + 선택입력 : 반복설정(5주까지 가능)
  
  <img src="https://user-images.githubusercontent.com/31336580/54277470-4fc16080-45d3-11e9-9e38-7de3cfb6a5cc.png" width="90%"></img>
  
  - 반복설정
    + 반복설정 요청기간 중 회의실이 기예약되어 있다면 기예약 전일까지는 회의실 예약 처리
      * ex)3월1일 부터 4주간예약(1일,8일,15일,22일) -> 15일이 기예약되어있다면, 1일, 8일까지 예약 처리
  
  <img src="https://user-images.githubusercontent.com/31336580/54277509-65cf2100-45d3-11e9-9e49-cbb8a46014c5.png" width="90%"></img>
  
  - 반복설정 결과
  
  <img src="https://user-images.githubusercontent.com/31336580/54277549-8303ef80-45d3-11e9-8bc2-3941d97524ce.png" width="90%"></img>
  
  - 중첩예약불가
    + 예약요청기간 사이 기예약 시간이 있다면 
      * 10:00~15:00 요청 ->13:00:1400 시간이 예약되어있다면 예약불가
      
  <img src="https://user-images.githubusercontent.com/31336580/54277565-8f884800-45d3-11e9-9c9d-0632d03b377f.png" width="90%"></img>

### 단위테스트

 - Junit사용
  
 <img src="https://user-images.githubusercontent.com/31336580/54278414-a039bd80-45d5-11e9-8430-1f13cdc6d268.png" width="90%"></img>
 
 <img src="https://user-images.githubusercontent.com/31336580/54278457-b8a9d800-45d5-11e9-8dc7-bf1ff6c82431.png" width="90%"></img>
 
  
### 실행방법
  - JAVA 1.8
  - Git Code Download -> Project Import -> Maven Project
  - Default port : 8888
  - Main 화면 : http://localhost:8888/room/roomMain
 
 
 ### 후기
   - 야근에 회식에 개발할 시간이..ㅜ.ㅠ
   - 안써보던거 쓰려니 엄청난 삽질의 연속 하하하(JPA넘나 신기한 부분)
   - 추후 시간이 된다면 최초 계획했던 사용자관리 화면 및 스프링시큐리티를 활용한 로그인과 
      Filter, Interceptor 활용한 injection 방어까지 개발해서 올리자(언제까지?)

