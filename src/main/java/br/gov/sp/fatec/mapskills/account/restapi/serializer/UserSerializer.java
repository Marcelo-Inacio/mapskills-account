/*
 * @(#)UserSerializer.java 1.0 10/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.account.restapi.serializer;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.account.restapi.wrapper.UserWrapper;
import br.gov.sp.fatec.mapskills.account.user.ProfileType;
import br.gov.sp.fatec.mapskills.account.user.User;
import br.gov.sp.fatec.mapskills.account.util.ApplicationContextHolder;
import lombok.AllArgsConstructor;
/**
 * 
 * A classe {@link UserSerializer} e responsavel por serializar um perfil de usuario.
 * A serializacao e feita a partir da recuperacao do serializador especifico esta
 * contido no mapa de serializadores, cuja chave e o perfil do usuario.
 *
 * @author Marcelo
 * @version 1.0 10/11/2016
 */
@Component
@AllArgsConstructor
public class UserSerializer extends JsonSerializer<UserWrapper> {
		
	@Override
	public void serialize(final UserWrapper wrapper, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		@SuppressWarnings("unchecked")
		final JsonSerializer<User> serializer = getSerializer(wrapper.getProfile());
		gen.writeStartObject();
		serializer.serialize(wrapper.getUser(), gen, serializers);
		gen.writeEndObject();
	}
	
	/**
	 * Mapa de definicao de serializadores para os perfis.
	 * 
	 * @see <code>SerializersConfig</code>.
	 */
	@SuppressWarnings("rawtypes")
	private JsonSerializer getSerializer(final ProfileType profile) {
		@SuppressWarnings("unchecked")
		final Map<ProfileType, JsonSerializer> map = ApplicationContextHolder.getBean("userSerializerMap", Map.class);
		return map.get(profile);
	}
}