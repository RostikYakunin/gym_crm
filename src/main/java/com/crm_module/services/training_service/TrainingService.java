package com.crm_module.services.training_service;

import com.crm_module.models.training.Training;
import org.springframework.stereotype.Service;

@Service
public interface TrainingService {
    Training findById(long id);

    Training save(Training training);

    boolean isExistsById(long id);
}
