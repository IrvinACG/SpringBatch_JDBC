package com.iacg.batch.step;

import java.nio.charset.StandardCharsets;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import com.iacg.batch.model.PersonModel;
import com.iacg.batch.utils.ColumnBatch;

/**
 * Clase que encargada de leer el archivo CSV y mapear a Clase
 * 
 * @author IACG
 */
public class PersonItemReader extends FlatFileItemReader<PersonModel>{
	
	/**
	 * Constructor de Clase que contiene la configuracion, para leer el archivo CSV
	 */
	public PersonItemReader(String pathFile) {
		setName("readPersons");
		setResource(new ClassPathResource(pathFile)); //Ubicacion del archivo
		setLinesToSkip(1); //Salta la primera linea de columnas
		setEncoding(StandardCharsets.UTF_8.name());
		setLineMapper(getLineMapper());
		setStrict(false); //No necesita que el archivo exista antes de la ejecución
	}
	
	/**
	 * Metodo que crea la configuracion para leer y mapear las lineas del CSV a Clase
	 * @return LineMapper
	 */
	public LineMapper<PersonModel> getLineMapper() {
		DefaultLineMapper<PersonModel> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		
		//Agregar la informacion de las columnas del archivo CSV
		lineTokenizer.setNames(ColumnBatch.COLUMNS_PERSON);
		lineTokenizer.setIncludedFields(ColumnBatch.INDEX_FIELDS_PERSON);
		lineTokenizer.setDelimiter(","); //Seperador del archivo CSV
		
		//Mapea las columnas de CSV con la Clase
		BeanWrapperFieldSetMapper<PersonModel> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(PersonModel.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		return lineMapper;
	}
}//Fin de clase
