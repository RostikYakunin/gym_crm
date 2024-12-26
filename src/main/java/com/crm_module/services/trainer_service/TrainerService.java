package com.crm_module.services.trainer_service;

import com.crm_module.models.users.impl.Trainer;
import org.springframework.stereotype.Service;

@Service
public interface TrainerService {
    Trainer findById(long id);

    Trainer save(Trainer trainer);

    boolean isExistsById(long id);
}
