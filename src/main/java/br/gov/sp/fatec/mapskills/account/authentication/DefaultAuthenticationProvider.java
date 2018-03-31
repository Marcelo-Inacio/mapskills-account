/*
 * @(#)DefaultAuthenticationProvider.java 1.0 27/01/2017
 *
 * Copyright (c) 2017, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account.authentication;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.mapskills.account.user.User;
import br.gov.sp.fatec.mapskills.account.user.UserRepository;
import br.gov.sp.fatec.mapskills.account.util.ApplicationContextHolder;

/**
 * A classe {@link DefaultAuthenticationProvider} responsavel
 * por realizar as autenticacoes dos usuarios na aplicacao.
 *
 * @author Marcelo
 * @version 1.0 27/01/2017
 */
@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(final Authentication authentication) {
		final String username = ((String) authentication.getPrincipal()).toLowerCase();
		final String password = (String) authentication.getCredentials();
		
		final Optional<User> user = getUser(username);
		if (!user.isPresent()) {
			throw new BadCredentialsException(username + " nao possui permissao");
		}
		return authenticate(user.get(), password);
	}

	@Override
	public boolean supports(final Class<?> arg0) {
		return true;
	}
	
	private Optional<User> getUser(final String username) {
		final UserRepository userRepository = ApplicationContextHolder.getBean("userRepository", UserRepository.class);
		return userRepository.findByUsername(username);
	}
	
	private Authentication authenticate(final User user, final String password) {
		final PasswordEncoder encoder = ApplicationContextHolder.getBean("encoder", PasswordEncoder.class);
		if (!encoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("username/password invalid");
		}
		return new PreAuthenticatedAuthentication(user);
	}
}