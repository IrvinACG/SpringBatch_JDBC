package com.iacg.batch.step;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase encargada de monitorear cuando el trabajo de batch inicio y finalizo
 * 
 * @author IACG
 */
@Slf4j
public class JobCompletionNotificationListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("---- INICIA PROCESO BATCH ----");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
    	log.info("---- TERMINA PROCESO BATCH ----");
    }
}//Fin de clase