/*
 * @(#)Mentor.java 1.0 01/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.account.user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;

/**
 * 
 * A classe {@link Mentor}
 *
 * @author Marcelo
 * @version 1.0 01/11/2016
 */
@Getter
@Entity
@Table(name = "MAPSKILLS.MENTOR")
public class Mentor extends User {

	@ManyToOne
	@JoinColumn(name = "ID_INSTITUTION")
	private Institution institution;

	@SuppressWarnings("unused")
	private Mentor() {
		this(null, null, null);
	}
	
	public Mentor(final String name, final String username, final String password) {
		super(name, new Login(username, password));
	}

	@Override
	public ProfileType getProfile() {
		return ProfileType.MENTOR;
	}
}