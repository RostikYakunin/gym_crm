package com.crm_module.repositories.trainer_repo.impl;


import com.crm_module.models.users.User;
import com.crm_module.models.users.impl.Trainer;
import com.crm_module.repositories.trainer_repo.TrainerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TrainerRepoImpl implements TrainerRepo {
    private final Map<Long, Trainer> trainerDataBase;

    @Override
    public Optional<Trainer> findById(long id) {
        log.debug("Trying to find trainer by id=" + id);
        return Optional.ofNullable(trainerDataBase.get(id));
    }

    @Override
    public Trainer save(Trainer trainer) {
        log.debug("Trying to save trainer by id=" + trainer.getUserId());
        return trainerDataBase.put(trainer.getUserId(), trainer);
    }

    @Override
    public Trainer update(Trainer trainer) {
        log.debug("Trying to update trainer by id=" + trainer.getUserId());
        return trainerDataBase.put(trainer.getUserId(), trainer);
    }

    @Override
    public boolean deleteById(long id) {
        log.debug("Trying to remove trainer by id=" + id);
        trainerDataBase.remove(id);

        log.debug("Trainer with id=" + id + " was successfully removed");
        return trainerDataBase.containsKey(id);
    }

    @Override
    public boolean isExistsById(long id) {
        log.debug("Trying to check if exists trainer with id=" + id);
        return trainerDataBase.containsKey(id);
    }

    @Override
    public boolean isUserNameExists(String username) {
        log.debug("Trying to check if trainer with username=" + username + " is existed");
        return trainerDataBase.values()
                .stream()
                .map(User::getUsername)
                .anyMatch(un -> un.equals(username));
    }
}
