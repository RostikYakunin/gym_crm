package com.crm_module;

import com.crm_module.training.TrainingService;
import com.crm_module.users.trainee.TraineeService;
import com.crm_module.users.trainer.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GymFacade {
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;
}
