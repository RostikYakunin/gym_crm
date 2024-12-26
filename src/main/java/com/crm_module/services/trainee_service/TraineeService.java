package com.crm_module.services.trainee_service;

import com.crm_module.models.users.impl.Trainee;
import org.springframework.stereotype.Service;

@Service
public interface TraineeService {
    Trainee findById(long id);

    Trainee save(Trainee trainee);

    boolean isExistsById(long id);
}
