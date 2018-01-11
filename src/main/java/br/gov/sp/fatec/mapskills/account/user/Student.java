/*
 * @(#)Student.java 1.0 01/11/2016
 *
 * Copyright (c) 2016, Fatec Jessen Vidal. All rights reserved.
 * Fatec Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */
package br.gov.sp.fatec.mapskills.account.user;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "MAPSKILLS.STUDENT")
public class Student extends User {	
	
	@Embedded
	private final AcademicRegistry ra;
	
	@Column(name = "PHONE", nullable = false)
	private final String phone;
	
	@ManyToOne
	@JoinColumn(name = "ID_COURSE")
	private Course course;
	
	@SuppressWarnings("unused")
	private Student() {
		this("0000000000000", null, null, null, null);
	}
			
	public Student(final String ra, final String name, final String phone,
			final String username, final String password) {
		super(name, new Login(username, password));
		this.ra = new AcademicRegistry(ra);
		this.phone = phone;
	}
	
	public String getFullRa() {
		return ra.getFullRa();
	}
	
	public AcademicRegistry getAcademicRegistry() {
		return ra;
	}
	
	public String getCourseCode() {
		return ra.getCourseCode();
	}
	
	public String getInstitutionCode() {
		return ra.getInstitutionCode();
	}
	
	public Institution getInstitution() {
		return course.getInstitution();
	}

	@Override
	public ProfileType getProfile() {
		return ProfileType.STUDENT;
	}
}