package com.meetroom.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.meetroom.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReserveControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void verifyAllReserveList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/reserve").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	public void verifyReserveSaveFalse() throws Exception  {
		mockMvc.perform(MockMvcRequestBuilders.post("/reserve/")
		        .contentType(MediaType.APPLICATION_JSON)
		        //.content("{\"text\" : \"New ToDo Sample\", \"completed\" : \"false\" }")
		        .content("{\"romId\" : \"Room1\", \"usrNm\" : \"테스트\", \"rsvPw\" : \"테스트 내용\" , \"rsvDt\" : \"20190313\" , \"strCd\" : \"1\" , \"endCd\" : \"2\", \"completed\" : \"true\"  }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void verifyReserveSaveTrue() throws Exception  {
		mockMvc.perform(MockMvcRequestBuilders.post("/reserve/")
		        .contentType(MediaType.APPLICATION_JSON)
		        //.content("{\"text\" : \"New ToDo Sample\", \"completed\" : \"false\" }")
		        .content("{\"romId\" : \"Room1\", \"usrNm\" : \"테스트\", \"rsvPw\" : \"테스트 내용\" , \"rsvDt\" : \"20190320\" , \"strCd\" : \"1\" , \"endCd\" : \"2\", \"completed\" : \"true\"  }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void verifyReserveSaveTrue2() throws Exception  {
		mockMvc.perform(MockMvcRequestBuilders.post("/reserve/")
		        .contentType(MediaType.APPLICATION_JSON)
		        //.content("{\"text\" : \"New ToDo Sample\", \"completed\" : \"false\" }")
		        .content("{\"romId\" : \"Room1\", \"usrNm\" : \"테스트\", \"rsvPw\" : \"테스트 내용\" , \"rsvDt\" : \"20190313\" , \"strCd\" : \"9\" , \"endCd\" : \"10\", \"completed\" : \"true\"  }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void verifyReserveSaveException() throws Exception  {
		mockMvc.perform(MockMvcRequestBuilders.post("/reserve/")
		        .contentType(MediaType.APPLICATION_JSON)
		        //.content("{\"text\" : \"New ToDo Sample\", \"completed\" : \"false\" }")
		        .content("{\"romId\" : \"Room1\", \"usrNm\" : \"테스트\"0320\" , \"strCd\" : \"1\" , \"endCd\" : \"2\", \"completed\" : \"true\"  }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(400))
				.andDo(print());
	}
}
