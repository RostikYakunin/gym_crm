package com.crm.services;

import com.crm.models.training.TrainingType;
import com.crm.models.users.Trainer;

public interface TrainerService {
    Trainer findById(long id);

    Trainer save(String firstName, String lastName, TrainingType specialization);

    Trainer save(Trainer trainer);

    Trainer update(Trainer trainer);
}
