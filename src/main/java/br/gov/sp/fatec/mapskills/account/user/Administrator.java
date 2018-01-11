/*
 * @(#)Administrator.java 1.0 01/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.account.user;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * A classe {@link Administrator}
 *
 * @author Marcelo
 * @version 1.0 01/11/2016
 */
@Entity
@Table(name = "MAPSKILLS.ADMINISTRATOR")
public class Administrator extends User {
	
	@SuppressWarnings("unused")
	private Administrator() {
		this(null, null, null);
	}
		
	public Administrator(final String name, final String email, final String password) {
		super(name, new Login(email, password));
	}

	@Override
	public ProfileType getProfile() {
		return ProfileType.ADMINISTRATOR;
	}
}