package com.patientData.patientInformation.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private static final Logger logger = LogManager.getLogger(Config.class);

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
