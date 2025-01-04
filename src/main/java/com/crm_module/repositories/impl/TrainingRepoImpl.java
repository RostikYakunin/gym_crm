package com.crm_module.repositories.impl;

import com.crm_module.models.training.Training;
import com.crm_module.repositories.TrainingRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TrainingRepoImpl implements TrainingRepo {
    private static long idCounter = 1;
    private final Map<Long, Training> trainingDataBase;

    @Override
    public long generateId() {
        return idCounter++;
    }

    @Override
    public Optional<Training> findById(long id) {
        log.debug("Trying to find training by id=" + id);
        return Optional.ofNullable(trainingDataBase.get(id));
    }

    @Override
    public Training save(Training training) {
        log.debug("Trying to save training with id=" + training.getId());
        trainingDataBase.put(training.getId(), training);
        return training;
    }

    @Override
    public Training update(Training training) {
        log.debug("Trying to update training with id=" + training.getId());
        return trainingDataBase.put(training.getId(), training);
    }

    @Override
    public void delete(Training training) {
        var trainingId = training.getId();
        log.debug("Trying to remove training with id=" + trainingId);

        trainingDataBase.remove(trainingId);
        log.debug("Training with id=" + trainingId + " was successfully removed");
    }

    @Override
    public boolean isExistsById(long id) {
        log.debug("Trying to check if exists training with id=" + id);
        return trainingDataBase.containsKey(id);
    }
}
