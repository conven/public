package com.meetroom.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
	
	/*
	@Test
	public void verifyAllToDoList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todo").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}
	
	@Test
	public void verifyToDoById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todo/3").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.text").exists())
		.andExpect(jsonPath("$.completed").exists())
		.andExpect(jsonPath("$.id").value(3))
		.andExpect(jsonPath("$.text").value("Build the artifacts"))
		.andExpect(jsonPath("$.completed").value(false))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidToDoArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todo/f").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.errorCode").value(400))
			.andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
			.andDo(print());
	}
	
	@Test
	public void verifyInvalidToDoId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todo/0").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("ToDo doesn´t exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyNullToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todo/6").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("ToDo doesn´t exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyDeleteToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/todo/4").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("ToDo has been deleted"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidToDoIdToDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/todo/9").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("ToDo to delete doesn´t exist"))
		.andDo(print());
	}
	
	
	@Test
	public void verifySaveToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/todo/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"text\" : \"New ToDo Sample\", \"completed\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.text").exists())
		.andExpect(jsonPath("$.completed").exists())
		.andExpect(jsonPath("$.text").value("New ToDo Sample"))
		.andExpect(jsonPath("$.completed").value(false))
		.andDo(print());
	}
	
	@Test
	public void verifyMalformedSaveToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/todo/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"8\", \"text\" : \"New ToDo Sample\", \"completed\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Payload malformed, id must not be defined"))
		.andDo(print());
	}
	
	@Test
	public void verifyUpdateToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/todo/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"1\", \"text\" : \"New ToDo Text\", \"completed\" : \"false\" }")
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.text").exists())
		.andExpect(jsonPath("$.completed").exists())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.text").value("New ToDo Text"))
		.andExpect(jsonPath("$.completed").value(false))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidToDoUpdate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/todo/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"idd\": \"8\", \"text\" : \"New ToDo Sample\", \"completed\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("ToDo to update doesn´t exist"))
		.andDo(print());
	}
	*/

}
