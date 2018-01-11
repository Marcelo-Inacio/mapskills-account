/*
 * @(#)SecurityConfig.java 1.0 1 11/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account.config;

import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import br.gov.sp.fatec.mapskills.account.authentication.AuthenticationListener;

/**
 * A classe {@link SecurityConfig}
 *
 * @author Marcelo
 * @version 1.0 11/01/2018
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource({"classpath:authentication.properties"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    @Qualifier("providerManager")
    private AuthenticationManager providerManager;
	
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.addFilterBefore(corsFilter(), AbstractPreAuthenticatedProcessingFilter.class).csrf().disable();
		http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class).csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll();
	}
	
	/**
	 * Filtro que realiza o login do usuario na aplicacao
	 * passando pela rota padrao de login do spring security.
	 */
	@Bean
	public Filter loginFilter() {
		final UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(providerManager);
		filter.setAuthenticationSuccessHandler(successHandler);
		filter.setAuthenticationFailureHandler(failureHandler);
		return filter;
	}
	
	/**
	 * Configuracao do Cross-Origin Resource Sharing (CORS) da aplicacao.
	 */
	@Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }
	
	@Bean
	public AuthenticationManager providerManager(@Qualifier("defaultAuthenticationProvider") final AuthenticationProvider provider) {
		return new ProviderManager(Arrays.asList(provider));
	}
	
	/**
	 * Define um encriptador de senha para aplicacao.
	 */
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(5);
    }
	
	/**
	 * Lista de todos os listeners de sucesso ao realizar login.
	 * 
	 * @param responseHeaderListener
	 * 		Listener de escrita do JWT no header da response.
	 * @return
	 * 		A lista de listeners.
	 */
	@Bean
    public List<AuthenticationListener> authenticationListeners(
            @Qualifier("responseHeaderAuthenticationListener") final AuthenticationListener responseHeaderListener) {
        return Arrays.asList(responseHeaderListener);
    }
}