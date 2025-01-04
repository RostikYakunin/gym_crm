package com.crm_module.services;

import com.crm_module.models.users.Trainee;

public interface TraineeService {
    Trainee findById(long id);

    Trainee save(Trainee trainee);

    Trainee update(Trainee trainee);

    void delete(Trainee trainee);

    boolean isExistsById(long id);
}
