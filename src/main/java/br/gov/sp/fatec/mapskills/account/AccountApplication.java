/*
 * @(#)AccountApplication.java 1.0 1 11/01/2018
 *
 * Copyright (c) 2017, Fatec-Jessen Vidal. All rights reserved.
 * Fatec-Jessen Vidal proprietary/confidential. Use is subject to license terms.
 */

package br.gov.sp.fatec.mapskills.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A classe {@link AccountApplication} realiza o start
 * da aplicação.
 *
 * @author Marcelo
 * @version 1.0 11/01/2018
 */
@SpringBootApplication
public class AccountApplication {

	/**
	 * Inicializa a aplicacao Spring Boot.
	 * 
	 * @param args
	 * 		Argumentos metodo main.
	 */
	public static void main(final String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
}