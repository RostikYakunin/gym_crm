package com.crm_module.repositories;

import com.crm_module.models.users.Trainee;

public interface TraineeRepo extends BaseCrudRepo<Trainee> {
    long generateId();

    boolean isExistsById(long id);

    boolean isUserNameExists(String username);
}
