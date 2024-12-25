package com.crm_module.training;

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
