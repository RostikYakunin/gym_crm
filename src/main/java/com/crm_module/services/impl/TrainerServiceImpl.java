package com.crm_module.services.impl;

import com.crm_module.mapper.TrainerMapper;
import com.crm_module.models.users.Trainer;
import com.crm_module.repositories.TrainerRepo;
import com.crm_module.services.TrainerService;
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
    private final TrainerMapper trainerMapper;

    @Override
    public Trainer findById(long id) {
        log.info("Searching for trainer with id={}", id);
        return trainerRepo.findById(id).orElse(null);
    }

    @Override
    public Trainer save(String firstName, String lastName) {
        log.info("Starting saving trainer using first and last names... ");

        var newTrainer = Trainer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();

        return save(newTrainer);
    }

    @Override
    public Trainer save(Trainer trainer) {
        log.info("Starting saving trainer {}", trainer);

        var uniqueUsername = UserUtils.generateUniqueUsername(
                trainer,
                trainerRepo::isUserNameExists
        );
        var generatedPassword = UserUtils.generatePassword();

        trainer.setUserId(trainerRepo.generateId());
        trainer.setUsername(uniqueUsername);
        trainer.setPassword(generatedPassword);
        trainer.setActive(true);

        Trainer savedTrainer = trainerRepo.save(trainer);
        log.info("Trainer with id={} was successfully saved", savedTrainer.getUserId());

        return savedTrainer;
    }

    @Override
    public Trainer update(Trainer trainer) {
        var trainerUserId = trainer.getUserId();
        log.info("Starting update process for trainer with id={}", trainerUserId);

        var existingTrainer = trainerRepo.findById(trainerUserId)
                .orElseThrow(() -> {
                    log.error("Trainer with id={} not found, updating failed", trainerUserId);
                    return new NoSuchElementException("trainer with id=" + trainerUserId + " not found");
                });

        log.info("Starting updating trainer... ");
        trainerMapper.updateTrainer(existingTrainer, trainer);

        var updatedTrainer = trainerRepo.update(existingTrainer);
        log.info("Trainer with id={} was successfully updated", trainerUserId);

        return updatedTrainer;
    }

    @Override
    public boolean isExistsById(long id) {
        log.info("Checking existence of trainer with id={}", id);
        return trainerRepo.isExistsById(id);
    }
}
