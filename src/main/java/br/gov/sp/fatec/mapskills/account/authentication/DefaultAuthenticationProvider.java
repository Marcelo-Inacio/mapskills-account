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
import org.springframework.util.ObjectUtils;

import br.gov.sp.fatec.mapskills.account.user.User;
import br.gov.sp.fatec.mapskills.account.user.UserNotFoundException;
import br.gov.sp.fatec.mapskills.account.user.UserRepository;
import lombok.AllArgsConstructor;

/**
 * 
 * A classe {@link DefaultAuthenticationProvider} responsavel
 * por realizar as autenticacoes dos usuarios na aplicacao.
 *
 * @author Marcelo
 * @version 1.0 27/01/2017
 */
@Component
@AllArgsConstructor
public class DefaultAuthenticationProvider implements AuthenticationProvider {
		
	private final UserRepository userRepository;
	private final PasswordEncoder encoder;

	@Override
	public Authentication authenticate(final Authentication authentication) {
		final String username = ((String) authentication.getPrincipal()).toLowerCase();
		final String password = (String) authentication.getCredentials();
		
		final Optional<User> user = userRepository.findByUsername(username);
		if (!user.isPresent()) {
			throw new UserNotFoundException(username);
		}
		return authenticate(user.get(), password);		
	}

	@Override
	public boolean supports(final Class<?> arg0) {
		return true;
	}
	
	private Authentication authenticate(final User user, final String password) {
		if (ObjectUtils.isEmpty(user) || !encoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("username/password invalid");
		}
		return new PreAuthenticatedAuthentication(user);
	}
}