package com.crm_module.app_config;

import com.crm_module.training.Training;
import com.crm_module.users.trainee.Trainee;
import com.crm_module.users.trainer.Trainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
@Import(AppConfig.class)
public class DataInitializer {
    @Value("${data.file.trainee_data}")
    private String traineeDataFilePath;

    @Value("${data.file.trainer_data}")
    private String trainerDataFilePath;

    @Value("${data.file.training_data}")
    private String trainingDataFilePath;

    private final Map<Long, Trainee> traineeDataBase;
    private final Map<Long, Trainer> trainerDataBase;
    private final Map<Long, Training> trainingDataBase;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void initializeData() {
        try {
            initializeTraineeData();
            initializeTrainerData();
            initializeTrainingData();
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong with file deserialization", e);
        }
    }

    private void initializeTraineeData() throws Exception {
        List<Trainee> trainees = List.of(objectMapper.readValue(new File(traineeDataFilePath), Trainee[].class));
        trainees.forEach(trainee -> traineeDataBase.put(trainee.getUserId(), trainee));
    }

    private void initializeTrainerData() throws Exception {
        List<Trainer> trainers = List.of(objectMapper.readValue(new File(trainerDataFilePath), Trainer[].class));
        trainers.forEach(trainer -> trainerDataBase.put(trainer.getUserId(), trainer));
    }

    private void initializeTrainingData() throws Exception {
        List<Training> trainings = List.of(objectMapper.readValue(new File(trainingDataFilePath), Training[].class));
        trainings.forEach(training -> trainingDataBase.put(training.getId(), training));
    }
}
