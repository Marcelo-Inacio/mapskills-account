/*
 * @(#)StudentSerializer.java 1.0 10/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account.restapi.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.account.user.Course;
import br.gov.sp.fatec.mapskills.account.user.Institution;
import br.gov.sp.fatec.mapskills.account.user.Student;
import lombok.AllArgsConstructor;

/**
 * 
 * A classe {@link StudentSerializer} e responsavel
 * por serializar o perfil de aluno com suas respectivas
 * informacoes.
 *
 * @author Marcelo
 * @version 1.0 10/11/2016
 */
@Component
@AllArgsConstructor
public class StudentSerializer extends DefaultUserSerializer<Student> {
	
	private final InstitutionSerializer institutionSerializer;
	private final CourseSerializer courseSerializer;
	
	@Override
	public void serialize(final Student student, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		super.serialize(student, gen, serializers);
		gen.writeStringField("ra", student.getFullRa());
		gen.writeStringField("courseCode", student.getCourseCode());
		gen.writeStringField("phone", student.getPhone());
		courseSerialize(student.getCourse(), gen, serializers);
		institutionSerialize(student.getInstitution(), gen, serializers);
	}
	
	private void courseSerialize(final Course course, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		gen.writeObjectFieldStart("course");
		courseSerializer.serialize(course, gen, serializers);
		gen.writeEndObject();		
	}

	private void institutionSerialize(final Institution institution, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		gen.writeObjectFieldStart("institution");
		institutionSerializer.serialize(institution, gen, serializers);
		gen.writeEndObject();
	}
}