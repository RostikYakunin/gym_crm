package com.crm_module.training;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrainingRepoImpl implements TrainingRepo {
    private final Map<Long, Training> trainingDataBase;

    @Override
    public Optional<Training> findById(long id) {
        return Optional.ofNullable(trainingDataBase.get(id));
    }

    @Override
    public Training save(Training training) {
        return trainingDataBase.put(training.getId(), training);
    }

    @Override
    public Training update(Training training) {
        return trainingDataBase.put(training.getId(), training);
    }

    @Override
    public boolean deleteById(long id) {
        trainingDataBase.remove(id);
        return trainingDataBase.containsKey(id);
    }

    @Override
    public boolean isExistsById(long id) {
        return trainingDataBase.containsKey(id);
    }
}
