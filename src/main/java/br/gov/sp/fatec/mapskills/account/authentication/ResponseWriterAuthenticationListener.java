/*
 * @(#)ResponseWriterAuthenticationListener.java 1.0 1 05/02/2016
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account.authentication;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.mapskills.account.restapi.wrapper.UserWrapper;

/**
 * A classe {@link ResponseWriterAuthenticationListener} é responsável por
 * retornar os dados do usuário logado com sucesso na aplicação. Na prática, os
 * dados do usuário são escritos em formato JSON no objeto
 * <code>OutputStream</code> do objeto <code>HttpServletResponse</code>.
 *
 * @author Roberto Perillo
 * @version 1.0 05/02/2016
 */
@Component
public class ResponseWriterAuthenticationListener implements AuthenticationListener {

	/** {@inheritDoc} */
	@Override
	public void onAuthenticationSuccess(final AuthenticationEvent event) throws IOException {	
		final String json = new ObjectMapper().writeValueAsString(new UserWrapper(event.getUserDomain()));
        event.getResponse().setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        event.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
        event.getResponse().getOutputStream().print(json);
	}
}