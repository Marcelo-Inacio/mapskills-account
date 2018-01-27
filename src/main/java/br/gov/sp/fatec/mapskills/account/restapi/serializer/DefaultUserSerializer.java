/*
 * @(#)DefaultUserSerializer.java 1.0 10/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.account.restapi.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.account.user.User;

/**
 * 
 * A classe {@link DefaultUserSerializer} e responsavel
 * por serializar os atributos basicos de um perfil da
 * aplicacao.
 *
 * @author Marcelo
 * @version 1.0 10/11/2016
 */
@Component
public class DefaultUserSerializer<T extends User> extends JsonSerializer<T> {
	
	/** {@inheritDoc} */
	@Override
	public void serialize(final T user, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		gen.writeNumberField("id", user.getId());
		gen.writeStringField("name", user.getName());
		gen.writeStringField("profile", user.getProfile().name());
		gen.writeStringField("username", user.getUsername());
	}	
}