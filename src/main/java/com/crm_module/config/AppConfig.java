package com.crm_module.config;

import com.crm_module.models.training.Training;
import com.crm_module.models.users.Trainee;
import com.crm_module.models.users.Trainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.crm_module")
public class AppConfig {
    @Bean
    public Map<Long, Trainee> traineeDataBase() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long, Trainer> trainerDataBase() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long, Training> trainingDataBase() {
        return new HashMap<>();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;
    }
}
