package com.crm_module.users.trainer;

import org.springframework.stereotype.Service;

@Service
public interface TrainerService {
    Trainer findById(long id);

    Trainer save(Trainer trainer);

    boolean isExistsById(long id);
}
