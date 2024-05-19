package com.iacg.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.iacg.batch.model.PersonModel;
import com.iacg.batch.step.JobCompletionNotificationListener;
import com.iacg.batch.step.PersonItemProcessor;
import com.iacg.batch.step.PersonItemReader;
import com.iacg.batch.step.PersonItemWriter;

/**
 * Clase de configuracion de SpringBatch
 * 
 * @author IACG
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	/**
	 * Variable filePerson
	 */
	@Value("${batch.files.persons}")
	private String filePerson;
	
	/**
	 * Variable dataSize
	 */
	@Value("${batch.data.size}")
	private int dataSize;

	/**
	 * Variable jobBuilderFactory
	 */
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	/**
	 * Variable stepBuilderFactory
	 */
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	/**
	 * Crea bean PersonItemReader
	 * @return PersonItemReader
	 */
	@Bean
	PersonItemReader personItemReader() {
		return new PersonItemReader(filePerson);
	}
	
	/**
	 * Crea bean PersonItemWriter
	 * @return PersonItemWriter
	 */
	@Bean
	PersonItemWriter personItemWriter() {
		return new PersonItemWriter();
	}
	
	/**
	 * Crea bean PersonItemProcessor
	 * @return PersonItemProcessor
	 */
	@Bean
	PersonItemProcessor personItemProcessor() {
		return new PersonItemProcessor();
	}
	
	/**
	 * Crea bean Step encargado de configurar los pasos a ejcutar
	 * @return Step
	 */
	@Bean
	Step readFilePersonStep() {
		return stepBuilderFactory.get("readFilePersonStep")
				.<PersonModel, PersonModel>chunk(dataSize)
				.reader(personItemReader())
				.processor(personItemProcessor())
				.writer(personItemWriter())
				.taskExecutor(taskExecutor())
				.build();
	}
	
	/**
	 * Crea bean Jon encargado de ejcutar el Step
	 * @return Job
	 */
	@Bean
	Job importPersonJob() {
		return jobBuilderFactory.get("importPersonJob")
				.incrementer(new RunIdIncrementer())  // Añade un nuevo ID de ejecución para cada ejecución del job
				.listener(listener())
				.start(readFilePersonStep())
				.build();
	}
	
	/**
	 * Crea bean JobExecutionListener
	 * @return JobExecutionListener
	 */
	@Bean
	JobExecutionListener listener() {
		return new JobCompletionNotificationListener();
	}
	
	/**
	 * Crea bean encargado de generar mas hilos
	 * @return TaskExecutor
	 */
	@Bean
	TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(10);
		return asyncTaskExecutor;
	}
}//Fin de clase
