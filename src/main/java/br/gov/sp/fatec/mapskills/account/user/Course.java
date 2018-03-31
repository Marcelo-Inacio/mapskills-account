/*
 * @(#)Course.java 1.0 01/11/2016
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
/**
 * 
 * A classe {@link Course} representa um curso ao qual
 * uma intituicao pertence.
 *
 * @author Marcelo
 * @version 1.0 01/11/2016
 */
@Getter
@Entity
@Table(name = "MAPSKILLS.COURSE")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CODE")
	private final String code;
	
	@Column(name = "NAME")
	private final String name;
	
	@Enumerated
	@Column(name = "_PERIOD")
	private final Period period;
	
	@ManyToOne
	@JoinColumn(name = "ID_INSTITUTION")
	private final Institution institution;
	
	@SuppressWarnings("unused")
	private Course() {
		this(null, null, null, null);
	}
	
	public Course(final String code, final String name, final Period period,
			final Institution institution) {
		this.code = code;
		this.name = name;
		this.period = period;
		this.institution = institution;
	}
		
	public String getNamePeriod() {
		return period.name();
	}
	
	public String getInstitutionCode() {
		return institution.getCode();
	}
}