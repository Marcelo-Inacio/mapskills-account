/*
 * @(#)SerializersConfig.java 1.0 10/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.account.config;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.JsonSerializer;

import br.gov.sp.fatec.mapskills.account.user.Administrator;
import br.gov.sp.fatec.mapskills.account.user.Mentor;
import br.gov.sp.fatec.mapskills.account.user.ProfileType;
import br.gov.sp.fatec.mapskills.account.user.Student;

/**
 * 
 * A classe {@link SerializersConfig} possui uma configuracao de estrategia de
 * serializacao de perfil, onde cada perfil possui seu serializador.
 *
 * @author Marcelo
 * @version 1.0 10/11/2016
 */
@Configuration
public class SerializersConfig {
	
	/**
	 * Define um serializador para cada perfil de usuario da aplicacao.
	 */
	@Bean("userSerializerMap")
	@SuppressWarnings("rawtypes")
	public Map<ProfileType, JsonSerializer> userSerializerMap(
			@Qualifier("defaultUserSerializer") final JsonSerializer<Administrator> defaultSerializer,
			@Qualifier("studentSerializer") final JsonSerializer<Student> studentSerializer,
			@Qualifier("mentorSerializer") final JsonSerializer<Mentor> mentorSerializer) {
		
		final Map<ProfileType, JsonSerializer> map = new EnumMap<>(ProfileType.class);
		map.put(ProfileType.ADMINISTRATOR, defaultSerializer);
		map.put(ProfileType.MENTOR, mentorSerializer);
		map.put(ProfileType.STUDENT, studentSerializer);
		return map;
	}
}