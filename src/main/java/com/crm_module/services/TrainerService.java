package com.crm_module.services;

import com.crm_module.models.users.Trainer;

public interface TrainerService {
    Trainer findById(long id);

    Trainer save(String firstName, String lastName);

    Trainer save(Trainer trainer);

    Trainer update(Trainer trainer);

    boolean isExistsById(long id);
}
