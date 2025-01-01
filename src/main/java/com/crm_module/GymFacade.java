package com.crm_module;

import com.crm_module.services.TraineeService;
import com.crm_module.services.TrainerService;
import com.crm_module.services.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GymFacade {
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;
}
