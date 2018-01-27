/*
 * @(#)DefaultAuthenticationSuccessHandler.java 1.0 03/09/2015
 *
 * Copyright (c) 2017, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.account.authentication;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * A classe {@link DefaultAuthenticationSuccessHandler} é responsável por
 * invocar os <i>listeners</i> do evento de autenticação com sucesso do usuário
 * na aplicação.
 *
 * @author Roberto Perillo
 * @version 1.0 03/09/2015
 */
@Component
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private final List<AuthenticationListener> authenticationListeners = new LinkedList<>();

	/**
     * Itera a lista de objetos <code>AuthenticationSuccessHandler</code>,
     * invocando o método <code>onAuthenticationSuccess</code> de cada objeto.
     * Cada objeto faré uma operação de login com sucesso.
     * 
     * @param request
     *            O objeto que representa a requisição HTTP.
     * @param response
     *            O objeto que representa a resposta (onde o valor da
     *            <code>String</code> randomica será colocada como cabeçalho).
     * @param auth
     *            O objeto que representa o usuário autenticado.
     */
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication auth) throws IOException, ServletException {

		final AuthenticationEvent event = new AuthenticationEvent(response, auth);
        for (final AuthenticationListener listener : authenticationListeners) {
            listener.onAuthenticationSuccess(event);
        }
	}
	
	@Resource
    public void setAuthenticationListeners(final List<AuthenticationListener> authenticationListeners) {
        this.authenticationListeners.addAll(authenticationListeners);
    }
}