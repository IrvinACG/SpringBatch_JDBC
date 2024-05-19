package com.iacg.batch.utils;

/**
 * Clase que contiene la informacion de las columnas, relacionadas con el archivo a leer
 * 
 * @author IACG
 */
public class ColumnBatch {
	
	/**
	 * Constructor privado que evita la instancia
	 */
	private ColumnBatch() {}
	/**
	 * Constante COLUMNS_PERSON
	 */
	public static final String COLUMNS_PERSON[] = new String[] {"name", "lastName", "email", "phone", "age"};
	
	/**
	 * Constante INDEX_FIELDS_PERSON
	 */
	public static final int INDEX_FIELDS_PERSON[] = new int[] {0, 1, 2, 3, 4};	

}//Fin de clase

