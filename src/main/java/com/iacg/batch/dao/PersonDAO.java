package com.iacg.batch.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iacg.batch.model.PersonModel;

/**
 * Clase Repositorio, que contiene la logica para interactuar con la BD
 * 
 * @author IACG
 */
@Repository
public class PersonDAO {
	
	/**
	 * Variable dataSize
	 */
	@Value("${batch.data.size}")
	private int dataSize;
	
	/**
	 * Variable jdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Metodo encargado de guardar una lista de registros
	 * 
	 * @param personList Lista
	 */
	@Transactional
	public void saveAll(List<PersonModel> personList) {
		
		String sql = "INSERT INTO persons (name, last_name, email, phone, age, create_at) VALUES (?,?,?,?,?,?)";
		
		jdbcTemplate.batchUpdate(sql, personList, dataSize, 
				(PreparedStatement ps, PersonModel person) -> {
					ps.setString(1, person.getName());
					ps.setString(2, person.getLastName());
					ps.setString(3, person.getEmail());
					ps.setString(4, person.getPhone());
					ps.setInt(5, person.getAge());
					ps.setString(6, person.getCreateAt());
				});
		
	}

}//Fin de clase
