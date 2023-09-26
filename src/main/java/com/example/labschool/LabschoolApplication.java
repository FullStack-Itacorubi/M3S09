package com.example.labschool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabschoolApplication {
    
    private static Logger LOGGER = LoggerFactory.getLogger(LabschoolApplication.class);

	public static void main(String[] args) {
                LOGGER.info("INICIANDO API LABSCHOOL...");          
		SpringApplication.run(LabschoolApplication.class, args);
                LOGGER.info("API INICIADA COM SUCESSO.");
	}

}
