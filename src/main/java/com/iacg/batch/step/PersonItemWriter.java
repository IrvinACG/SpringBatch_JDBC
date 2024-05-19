package com.iacg.batch.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.iacg.batch.dao.PersonDAO;
import com.iacg.batch.model.PersonModel;

/**
 * Clase que se encarga de escribir/guardar los datosc
 * 
 * @author IACG
 */
public class PersonItemWriter implements ItemWriter<PersonModel>{

	/**
	 * Variable personDAO
	 */
	@Autowired
	private PersonDAO personDAO;

	/**
	 * Metodo que se encarga de guardar los datos recibidos
	 */
	@Override
	public void write(List<? extends PersonModel> items) throws Exception {
		//Guardar datos
		personDAO.saveAll((List<PersonModel>) items);
	}

}//Fin de clase
