package com.crm_module.services.trainer_service.impl;

import com.crm_module.models.users.impl.Trainer;
import com.crm_module.repositories.trainer_repo.TrainerRepo;
import com.crm_module.services.trainer_service.TrainerService;
import com.crm_module.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepo trainerRepo;

    @Override
    public Trainer findById(long id) {
        log.info("Searching for trainer with id={}", id);
        return trainerRepo.findById(id)
                .orElseThrow(
                        () -> {
                            log.error("Trainer with id=" + id + " was not found");
                            return new NoSuchElementException("Trainer with id=" + id + " was not found");
                        }
                );
    }

    @Override
    public Trainer save(Trainer trainer) {
        log.warn("Saving trainee: {}", trainer);

        var uniqueUsername = UserUtils.generateUniqueUsername(
                trainer,
                trainerRepo::isUserNameExists
        );
        var generatedPassword = UserUtils.generatePassword();
        log.info("Successfully generated unique username and password");

        trainer.setUsername(uniqueUsername);
        trainer.setPassword(generatedPassword);

        Trainer savedTrainer = trainerRepo.save(trainer);
        log.info("Trainer with id={} was successfully saved", savedTrainer.getUserId());

        return savedTrainer;
    }

    @Override
    public boolean isExistsById(long id) {
        log.info("Checking existence of trainer with id={}", id);
        return trainerRepo.isExistsById(id);
    }
}
