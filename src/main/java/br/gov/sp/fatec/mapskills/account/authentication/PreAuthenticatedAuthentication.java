/*
 * @(#)PreAuthenticatedAuthentication.java 1.0 27/01/2017
 *
 * Copyright (c) 2017, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.account.authentication;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import br.gov.sp.fatec.mapskills.account.user.User;

/**
 * 
 * A classe {@link PreAuthenticatedAuthentication}
 *
 * @author Marcelo
 * @version 1.0 27/01/2017
 */
public class PreAuthenticatedAuthentication extends PreAuthenticatedAuthenticationToken {
	
	private static final long serialVersionUID = 1L;

    public PreAuthenticatedAuthentication(final User user) {
        super(user, new DefaultGrantedAuthority(user.getProfile()), buildAuthorities(user));
        setAuthenticated(true);
    }

    @Override
    public User getPrincipal() {
        return (User) super.getPrincipal();
    }
    
    /**
     * Cria as autorizacoes que o usuario logado possue.
     * 
     * @param user
     * 		Usuario da aplicacao.
     * @return 
     * 		Colecao de autorizacoes ao qual possui acesso.
     */
    private static Collection<GrantedAuthority> buildAuthorities(final User user) {
		return Arrays.asList(new DefaultGrantedAuthority(user.getProfile()));
	}
}