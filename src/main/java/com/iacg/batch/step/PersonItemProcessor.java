package com.iacg.batch.step;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;

import com.iacg.batch.model.PersonModel;

/**
 * Clase que se encarga de procesar el dato y transformarlo por otro de salida
 * 
 * @author IACG
 */
public class PersonItemProcessor implements ItemProcessor<PersonModel, PersonModel>{

	/**
	 * Metodo encargado de procesar los datos
	 * 
	 * @return PersonModel 
	 */
	@Override
	public PersonModel process(PersonModel item) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime date = LocalDateTime.now();
		//Realiza transformacion de datos
		item.setCreateAt(formatter.format(date));
		item.setName(item.getName().toUpperCase());
		item.setLastName(item.getLastName().toUpperCase());
		
		return item;
	}

}//Fin de clase
