/*
 * @(#)Institution.java 1.0 01/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.account.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

/**
 * 
 * A classe {@link Institution}
 *
 * @author Marcelo
 * @version 1.0 01/11/2016
 */
@Getter
@Entity
@Table(name = "MAPSKILLS.INSTITUTION")
public class Institution {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CODE", nullable = false, unique = true, length = 5)
	private final String code;
	
	@Column(name = "CNPJ", nullable = false, unique = true)
	private final Long cnpj;
	
	@Column(name = "COMPANY", nullable = false)
	private final String company;

	@Column(name = "CITY", nullable = false)
	private final String city;
	
	@Enumerated
	@Column(name = "LEVEL", nullable = false)
	private final InstitutionLevel level;

	@SuppressWarnings("unused")
	private Institution() {
		this(null, null, null, null, null);
	}

	public Institution (final String code, final Long cnpj, final String company,
			final InstitutionLevel level, final String city) {
		this.code = code;
		this.cnpj = cnpj;
		this.company = company;
		this.level = level;
		this.city = city;
	}
}