/*
 * @(#)AuthenticationListener.java 1.0 05/02/2016
 *
 * Copyright (c) 2017, Fatec Jessen Vidal. All rights reserved. Fatec Jessen Vidal
 * proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account.authentication;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * A classe {@link AuthenticationListener} define o método a ser
 * executado após uma autenticação com sucesso na aplicação. Cada implementação
 * dessa interface terá um propósito específico, como registrar o usuário no
 * cache, registrar o login no banco para efeitos de auditoria, etc.
 *
 * @author Roberto Perillo
 * @version 1.0 05/02/2016
 */
@FunctionalInterface
public interface AuthenticationListener {
	
	/**
     * Executa uma acao apos a autenticacao com sucesso na aplicacao.
     * 
     * @param event
     *            O evento de autenticacao.
     * @throws IOException
     *             Caso ocorra algum problema de I/O, como ao enviar um dado ao
     *             usuario por meio do <code>OutputStream</code> do objeto
     *             <code>HttpServletResponse</code>, presente no evento.
     * @throws ServletException
     *             Caso ocorra algum problema ao lidar com os objetos de
     *             requisicao do usuario.
     */
    void onAuthenticationSuccess(final AuthenticationEvent event) throws IOException, ServletException;
}