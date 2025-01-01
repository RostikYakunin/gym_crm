package com.crm_module.repositories;

import com.crm_module.models.users.Trainer;

public interface TrainerRepo extends BaseCrudRepo<Trainer> {
    long generateId();

    boolean isExistsById(long id);

    boolean isUserNameExists(String username);
}
