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
        return traineeRepo.findById(id)
                .orElseThrow(
                        () -> {
                            log.error("Trainee with id=" + id + " was not found");
                            return new NoSuchElementException("Trainee with id=" + id + " was not found");
                        }
                );
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

        Trainee savedTrainee = traineeRepo.save(trainee);
        log.info("Trainee with id={} was successfully saved", savedTrainee.getUserId());

        return savedTrainee;
    }

    @Override
    public Trainee update(long id, Trainee trainee) {
        log.info("Starting update process for trainee with id={}", id);

        Trainee existingTrainee = traineeRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Trainee with id={} not found, update failed", id);
                    return new NoSuchElementException("Trainee with id=" + id + " not found");
                });

        log.info("Starting updating trainee... ");
        traineeMapper.updateTrainee(existingTrainee, trainee);

        Trainee updatedTrainee = traineeRepo.update(existingTrainee);
        log.info("Trainee with id={} was successfully updated", id);

        return updatedTrainee;
    }

    @Override
    public boolean deleteById(long id) {
        log.info("Attempting to delete trainee with id: {}", id);

        if (!traineeRepo.isExistsById(id)) {
            log.error("Trainee with id={} not found, deletion failed", id);
            throw new NoSuchElementException("Trainee with id=" + id + " not found");
        }

        var isDeleted = traineeRepo.deleteById(id);
        if (isDeleted) {
            log.info("Trainee with id={} was successfully deleted", id);
        } else {
            log.error("Failed to delete trainee with id={}", id);
        }

        return isDeleted;
    }

    @Override
    public boolean delete(Trainee trainee) {
        log.info("Starting deleting trainee... ");
        return deleteById(trainee.getUserId());
    }

    @Override
    public boolean isExistsById(long id) {
        log.info("Checking existence of trainee with id={}", id);
        return traineeRepo.isExistsById(id);
    }
}
