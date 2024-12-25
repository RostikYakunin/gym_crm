package com.crm_module.users.trainee;

import com.crm_module.users.utils.UserUtils;
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
                        () -> new NoSuchElementException("Trainee with id=" + id + " was not found")
                );
    }

    @Override
    public Trainee save(Trainee trainee) {
        log.debug("Saving trainee: {}", trainee.getFirstName());

        var uniqueUsername = UserUtils.generateUniqueUsername(
                UserUtils.generateUserName(trainee),
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
    public Trainee update(Trainee trainee) {
        log.info("Updating trainee with id={}", trainee.getUserId());
        return traineeRepo.update(trainee);
    }

    @Override
    public boolean deleteById(long id) {
        log.info("Deleting trainee with id={}", id);
        return traineeRepo.deleteById(id);
    }

    @Override
    public boolean isExistsById(long id) {
        log.info("Checking existence of trainee with id={}", id);
        return traineeRepo.isExistsById(id);
    }
}
