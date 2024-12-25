package com.crm_module.training;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepo trainingRepo;

    @Override
    public Training findById(long id) {
        log.info("Searching for trainee with id={}", id);
        return trainingRepo.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Training with id=" + id + " was not found")
                );
    }

    @Override
    public Training save(Training training) {
        log.debug("Saving training: {}", training);

        var savedTraining = trainingRepo.save(training);
        log.info("Training with id={} was successfully saved", savedTraining.getId());

        return savedTraining;
    }

    @Override
    public boolean isExistsById(long id) {
        return trainingRepo.isExistsById(id);
    }
}
