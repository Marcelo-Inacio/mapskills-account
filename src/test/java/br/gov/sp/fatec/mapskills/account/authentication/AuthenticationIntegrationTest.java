/*
 * @(#)AuthenticationIntegrationTest.java 1.0 1 24/03/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account.authentication;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.gov.sp.fatec.mapskills.account.AccountApplication;

/**
 * A classe {@link AuthenticationIntegrationTest} contem os testes de
 * integracao para realizacao de login na aplicacao.
 *
 * @author Marcelo
 * @version 1.0 24/03/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthenticationIntegrationTest extends AbstractIntegrationTest {
	
	@Autowired
    private MockMvc mvc;
	
	@Test
	public void loginMentor() throws Exception {
		runSQLCommands("/br/gov/sp/fatec/mapskills/account/database/controller/institution/insert-institution.sql");
		final String jsonExpected = getJsonAsString("json/expectations/login/mentor.json");
		final MvcResult mvcResult = mvc.perform(post("/login")
			.param("username", "rafael.alves@fatec.sp.gov.br")
			.param("password", "mudar@123")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isOk())
			.andReturn();
		
		final Cookie cookie = mvcResult.getResponse().getCookie("Authorization");
		final String jsonResult = getJsonResult(mvcResult);
		
		assertNotNull(cookie);
		JSONAssert.assertEquals(jsonExpected, jsonResult, true);
	}
	
	@Test
	public void loginAdmin() throws Exception {
		runSQLCommands("/br/gov/sp/fatec/mapskills/account/database/controller/user/insert-admin-user.sql");
		final String jsonExpected = getJsonAsString("json/expectations/login/admin.json");
		final MvcResult mvcResult = mvc.perform(post("/login")
			.param("username", "admin@cps.sp.gov.br")
			.param("password", "mudar@123")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isOk())
			.andReturn();
		
		final Cookie cookie = mvcResult.getResponse().getCookie("Authorization");
		final String jsonResult = getJsonResult(mvcResult);
		
		assertNotNull(cookie);
		JSONAssert.assertEquals(jsonExpected, jsonResult, true);
	}
	
	@Test
	public void loginStudent() throws Exception {
		runSQLCommands("/br/gov/sp/fatec/mapskills/account/database/controller/user/insert-student-user.sql");
		final String jsonExpected = getJsonAsString("json/expectations/login/student.json");
		final MvcResult mvcResult = mvc.perform(post("/login")
				.param("username", "alana.manuela.lima@fatec.sp.gov.br")
				.param("password", "mudar@123")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk())
				.andReturn();
			
			final Cookie cookie = mvcResult.getResponse().getCookie("Authorization");
			final String jsonResult = getJsonResult(mvcResult);
			
			assertNotNull(cookie);
			JSONAssert.assertEquals(jsonExpected, jsonResult, true);
	}
	
	@Test
	public void loginUserUnauthorized() throws Exception {
		runSQLCommands("/br/gov/sp/fatec/mapskills/account/database/controller/institution/insert-institution.sql");
		final String jsonExpected = getJsonAsString("json/expectations/login/unauthorized.json");
		final String message = mvc.perform(post("/login")
			.param("username", "rafael.alves.silva@fatec.sp.gov.br")
			.param("password", "mudar_123")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isUnauthorized())
			.andReturn()
			.getResponse()
			.getContentAsString();
		JSONAssert.assertEquals(jsonExpected, message, false);
	}
	
	@Test
	public void loginUserPasswordInvalid() throws Exception {
		runSQLCommands("/br/gov/sp/fatec/mapskills/account/database/controller/institution/insert-institution.sql");
		final String jsonExpected = getJsonAsString("json/expectations/login/username-password-invalid.json");
		final String message = mvc.perform(post("/login")
			.param("username", "rafael.alves@fatec.sp.gov.br")
			.param("password", "mudar_123")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isUnauthorized())
			.andReturn()
			.getResponse()
			.getContentAsString();
		JSONAssert.assertEquals(jsonExpected, message, false);
	}

}