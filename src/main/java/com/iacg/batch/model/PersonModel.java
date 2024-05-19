package com.iacg.batch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase de representa una Entidad en la BD
 * 
 * @author IACG
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonModel {

	/**
	 * Variable id
	 */
	private Long id;
	
	/**
	 * Variable name
	 */
	private String name;
	
	/**
	 * Variable lastName
	 */
	private String lastName;
	
	/**
	 * Variable email
	 */
	private String email;
	
	/**
	 * Variable phone
	 */
	private String phone;
	
	/**
	 * Variable age
	 */
	private Integer age;
	
	/** 
	 * Variable createAt
	 */
	private String createAt;
	
}//Fin de clase
