package com.crm_module;

import com.crm_module.services.trainee_service.TraineeService;
import com.crm_module.services.trainer_service.TrainerService;
import com.crm_module.services.training_service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GymFacade {
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;
}
