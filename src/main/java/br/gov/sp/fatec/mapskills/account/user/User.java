/*
 * @(#)User.java 1.0 01/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.account.user;

import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
/**
 * A classe abstrata <code>User</code> e uma entidade que 
 * representa usuario generico que pode acessar a aplicacao.
 * 
 * @author Marcelo
 * @version 01/11/2016
 */
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements Principal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;

	@OneToOne
	@JoinColumn(name = "ID_LOGIN")
	private final Login login;
	
	public abstract ProfileType getProfile();
	
	@SuppressWarnings("unused")
	private User() {
		this(null, null);
	}
		
	public User(final String name, final Login login) {
		this.name = name;
		this.login = login;
	}
		
	public String getUsername() {
		return login.getUsername();
	}
	
	public String getPassword() {
		return login.getPassword();
	}
}