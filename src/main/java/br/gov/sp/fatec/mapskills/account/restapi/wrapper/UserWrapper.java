/*
 * @(#)UserWrapper.java 1.0 1 27/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account.restapi.wrapper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.sp.fatec.mapskills.account.restapi.serializer.UserSerializer;
import br.gov.sp.fatec.mapskills.account.user.ProfileType;
import br.gov.sp.fatec.mapskills.account.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A classe {@link UserWrapper}
 *
 * @author Marcelo
 * @version 1.0 27/01/2018
 */
@Getter
@AllArgsConstructor
@JsonSerialize(using = UserSerializer.class)
public class UserWrapper {
	
	private final User user;

	public ProfileType getProfile() {
		return user.getProfile();
	}
}