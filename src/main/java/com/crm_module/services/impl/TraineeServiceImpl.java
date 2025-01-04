package com.crm_module.services.impl;

import com.crm_module.mapper.TraineeMapper;
import com.crm_module.models.users.Trainee;
import com.crm_module.repositories.TraineeRepo;
import com.crm_module.services.TraineeService;
import com.crm_module.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TraineeServiceImpl implements TraineeService {
    private final TraineeRepo traineeRepo;
    private final TraineeMapper traineeMapper;

    @Override
    public Trainee findById(long id) {
        log.info("Searching for trainee with id={}", id);
        return traineeRepo.findById(id).orElse(null);
    }

    public Trainee save(String firstName, String lastName) {
        log.info("Starting saving trainee using first and last names... ");

        var newTrainee = Trainee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        return save(newTrainee);
    }

    @Override
    public Trainee save(Trainee trainee) {
        log.info("Saving trainee: {}", trainee.getFirstName());

        var uniqueUsername = UserUtils.generateUniqueUsername(
                trainee,
                traineeRepo::isUserNameExists
        );
        var generatedPassword = UserUtils.generatePassword();

        trainee.setUserId(traineeRepo.generateId());
        trainee.setUsername(uniqueUsername);
        trainee.setPassword(generatedPassword);
        trainee.setActive(true);

        var savedTrainee = traineeRepo.save(trainee);
        log.info("Trainee with id={} was successfully saved", savedTrainee.getUserId());

        return savedTrainee;
    }

    @Override
    public Trainee update(Trainee trainee) {
        var traineeId = trainee.getUserId();
        log.info("Starting update process for trainee with id={}", traineeId);

        var existingTrainee = traineeRepo.findById(traineeId)
                .orElseThrow(() -> {
                    log.error("Trainee with id={} not found, update failed", traineeId);
                    return new NoSuchElementException("Trainee with id=" + traineeId + " not found");
                });

        log.info("Starting updating trainee... ");
        traineeMapper.updateTrainee(existingTrainee, trainee);

        var updatedTrainee = traineeRepo.update(existingTrainee);
        log.info("Trainee with id={} was successfully updated", traineeId);

        return updatedTrainee;
    }

    @Override
    public void delete(Trainee trainee) {
        var traineeId = trainee.getUserId();
        log.info("Attempting to delete trainee with id: {}", traineeId);

        if (!traineeRepo.isExistsById(traineeId)) {
            log.error("Trainee with id={} not found, deletion failed", traineeId);
            throw new NoSuchElementException("Trainee with id=" + traineeId + " not found");
        }

        traineeRepo.delete(trainee);
        log.info("Trainee with id={} was successfully deleted", traineeId);
    }

    @Override
    public boolean isExistsById(long id) {
        log.info("Checking existence of trainee with id={}", id);
        return traineeRepo.isExistsById(id);
    }
}
