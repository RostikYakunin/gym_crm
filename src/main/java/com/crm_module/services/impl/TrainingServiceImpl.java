package com.crm_module.services.impl;

import com.crm_module.models.training.Training;
import com.crm_module.repositories.TrainingRepo;
import com.crm_module.services.TrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepo trainingRepo;

    @Override
    public Training findById(long id) {
        log.info("Searching for trainee with id={}", id);
        return trainingRepo.findById(id).orElse(null);
    }

    @Override
    public Training save(Training training) {
        log.info("Saving training: {}", training);

        training.setId(trainingRepo.generateId());
        var savedTraining = trainingRepo.save(training);
        log.info("Training with id={} was successfully saved", savedTraining.getId());

        return savedTraining;
    }

    @Override
    public boolean isExistsById(long id) {
        log.info("Checking existence of training with id={}", id);
        return trainingRepo.isExistsById(id);
    }
}
