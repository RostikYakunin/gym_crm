package com.crm_module.init;

import com.crm_module.config.AppConfig;
import com.crm_module.models.training.Training;
import com.crm_module.models.users.Trainee;
import com.crm_module.models.users.Trainer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
            log.info("TraineeDataBase`s initialization started ...");
            initializeTraineeData();
            log.info("TraineeDataBase`s initialization successfully completed");

            log.info("TrainerDataBase`s initialization started ...");
            initializeTrainerData();
            log.info("TrainerDataBase`s initialization successfully completed");

            log.info("TrainingDataBase`s initialization started ...");
            initializeTrainingData();
            log.info("TrainingDataBase`s initialization successfully completed");
        } catch (Exception e) {
            log.error("DataBase initialization failed ...");
            throw new RuntimeException("Something went wrong with file deserialization", e);
        }
    }

    private void initializeTraineeData() throws Exception {
        List<Trainee> trainees = objectMapper.readValue(new File(traineeDataFilePath), new TypeReference<>() {});
        trainees.forEach(trainee -> traineeDataBase.put(trainee.getUserId(), trainee));
    }

    private void initializeTrainerData() throws Exception {
        List<Trainer> trainers = objectMapper.readValue(new File(trainerDataFilePath), new TypeReference<>() {});
        trainers.forEach(trainer -> trainerDataBase.put(trainer.getUserId(), trainer));
    }

    private void initializeTrainingData() throws Exception {
        List<Training> trainings = objectMapper.readValue(new File(trainingDataFilePath), new TypeReference<>() {});
        trainings.forEach(training -> trainingDataBase.put(training.getId(), training));
    }
}
