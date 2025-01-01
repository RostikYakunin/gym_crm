package com.crm_module.services;

import com.crm_module.models.training.Training;

public interface TrainingService {
    Training findById(long id);

    Training save(Training training);

    boolean isExistsById(long id);
}
