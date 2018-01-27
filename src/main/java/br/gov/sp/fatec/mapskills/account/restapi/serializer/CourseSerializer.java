/*
 * @(#)CourseSerializer.java 1.0 1 23/08/2017
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account.restapi.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.account.user.Course;

/**
 * A classe {@link CourseSerializer} e responsavel
 * por serializar objeto curso.
 *
 * @author Marcelo
 * @version 1.0 23/08/2017
 */
@Component
public class CourseSerializer extends JsonSerializer<Course> {
	
	/** {@inheritDoc} */
	@Override
	public void serialize(final Course course, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		gen.writeNumberField("id", course.getId());
		gen.writeStringField("code", course.getCode());
		gen.writeStringField("name", course.getName());
		gen.writeStringField("period", course.getNamePeriod());
		gen.writeStringField("institutionCode", course.getInstitutionCode());
	}
}