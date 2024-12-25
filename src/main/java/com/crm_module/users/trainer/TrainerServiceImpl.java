package com.crm_module.users.trainer;

import com.crm_module.users.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainerServiceImpl implements TrainerService{
    private final TrainerRepo trainerRepo;

    @Override
    public Trainer findById(long id) {
        log.info("Searching for trainer with id={}", id);
        return trainerRepo.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Trainer with id=" + id + " was not found")
                );
    }

    @Override
    public Trainer save(Trainer trainer) {
        log.debug("Saving trainee: {}", trainer);

        var uniqueUsername = UserUtils.generateUniqueUsername(
                UserUtils.generateUserName(trainer),
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
