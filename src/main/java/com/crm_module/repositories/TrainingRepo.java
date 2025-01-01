package com.crm_module.repositories;

import com.crm_module.models.training.Training;

public interface TrainingRepo extends BaseCrudRepo<Training> {
    long generateId();
    boolean isExistsById(long id);
}
