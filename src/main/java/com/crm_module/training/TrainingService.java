package com.crm_module.training;

import org.springframework.stereotype.Service;

@Service
public interface TrainingService {
    Training findById(long id);

    Training save(Training training);

    boolean isExistsById(long id);
}
