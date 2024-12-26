package com.crm_module.repositories.training_repo.impl;

import com.crm_module.models.training.Training;
import com.crm_module.repositories.training_repo.TrainingRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TrainingRepoImpl implements TrainingRepo {
    private final Map<Long, Training> trainingDataBase;

    @Override
    public Optional<Training> findById(long id) {
        log.debug("Trying to find training by id=" + id);
        return Optional.ofNullable(trainingDataBase.get(id));
    }

    @Override
    public Training save(Training training) {
        log.debug("Trying to save training with id=" + training.getId());
        return trainingDataBase.put(training.getId(), training);
    }

    @Override
    public Training update(Training training) {
        log.debug("Trying to update training with id=" + training.getId());
        return trainingDataBase.put(training.getId(), training);
    }

    @Override
    public boolean deleteById(long id) {
        log.debug("Trying to remove training with id=" + id);
        trainingDataBase.remove(id);

        log.debug("Training with id=" + id + " was successfully removed");
        return trainingDataBase.containsKey(id);
    }

    @Override
    public boolean isExistsById(long id) {
        log.debug("Trying to check if exists training with id=" + id);
        return trainingDataBase.containsKey(id);
    }
}
