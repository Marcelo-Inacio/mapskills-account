/*
 * @(#)MentorSerializer.java 1.0 31/12/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account.restapi.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.account.user.Mentor;
import lombok.AllArgsConstructor;

/**
 * 
 * A classe {@link MentorSerializer} e responsavel
 * por serializar o perfil <i>Mentor</i> da aplicacao.
 *
 * @author Marcelo
 * @version 1.0 31/12/2016
 */
@Component
@AllArgsConstructor
public class MentorSerializer extends DefaultUserSerializer<Mentor> {
	
	private final InstitutionSerializer institutionSerializer;
	
	@Override
	public void serialize(final Mentor mentor, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		super.serialize(mentor, gen, serializers);
		gen.writeObjectFieldStart("institution");
		institutionSerializer.serialize(mentor.getInstitution(), gen, serializers);
		gen.writeEndObject();
	}
}