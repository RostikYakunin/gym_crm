package com.crm_module.repositories.training_repo;

import com.crm_module.models.training.Training;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingRepo {
    Optional<Training> findById(long id);

    Training save(Training training);

    Training update(Training training);

    boolean deleteById(long id);

    boolean isExistsById(long id);
}
