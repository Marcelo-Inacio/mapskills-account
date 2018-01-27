/*
 * @(#)InstitutionSerializer.java 1.0 07/01/2017
 *
 * Copyright (c) 2017, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account.restapi.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.sp.fatec.mapskills.account.user.Institution;

/**
 * 
 * A classe {@link InstitutionSerializer} e responsavel
 * por serializar algumas informacaoes adicionais sobre uma determinada
 * instituicao.
 *
 * @author Marcelo
 * @version 1.0 07/01/2017
 */
@Component
public class InstitutionSerializer extends JsonSerializer<Institution> {
	
	/** {@inheritDoc} */
	@Override
	public void serialize(final Institution institution, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		gen.writeNumberField("id", institution.getId());
		gen.writeStringField("code", institution.getCode());
		gen.writeNumberField("cnpj", institution.getCnpj());
		gen.writeStringField("company", institution.getCompany());
		gen.writeStringField("level", institution.getLevel().name());
		gen.writeStringField("city", institution.getCity());	
	}
}