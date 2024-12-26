package com.crm_module.services.trainee_service.impl;

import com.crm_module.models.users.impl.Trainee;
import com.crm_module.repositories.trainee_repo.TraineeRepo;
import com.crm_module.services.trainee_service.TraineeService;
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

    @Override
    public Trainee save(Trainee trainee) {
        log.warn("Saving trainee: {}", trainee.getFirstName());

        var uniqueUsername = UserUtils.generateUniqueUsername(
                trainee,
                traineeRepo::isUserNameExists
        );
        var generatedPassword = UserUtils.generatePassword();

        trainee.setUsername(uniqueUsername);
        trainee.setPassword(generatedPassword);

        Trainee savedTrainee = traineeRepo.save(trainee);
        log.info("Trainee with id={} was successfully saved", savedTrainee.getUserId());

        return savedTrainee;
    }

    @Override
    public boolean isExistsById(long id) {
        log.info("Checking existence of trainee with id={}", id);
        return traineeRepo.isExistsById(id);
    }
}
