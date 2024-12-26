package com.crm_module.repositories.trainee_repo.impl;

import com.crm_module.models.users.User;
import com.crm_module.models.users.impl.Trainee;
import com.crm_module.repositories.trainee_repo.TraineeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TraineeRepoImpl implements TraineeRepo {
    private final Map<Long, Trainee> traineeDataBase;

    @Override
    public Optional<Trainee> findById(long id) {
        log.debug("Trying to find trainee by id=" + id);
        return Optional.ofNullable(traineeDataBase.get(id));
    }

    @Override
    public Trainee save(Trainee trainee) {
        log.debug("Trying to save trainee with id=" + trainee.getUserId());
        return traineeDataBase.put(trainee.getUserId(), trainee);
    }

    @Override
    public Trainee update(Trainee trainee) {
        log.debug("Trying to update trainee with id=" + trainee.getUserId());
        return save(trainee);
    }

    @Override
    public boolean deleteById(long id) {
        log.debug("Trying to remove trainee with id=" + id);
        traineeDataBase.remove(id);

        log.debug("Trainee with id=" + id + " was successfully removed");
        return traineeDataBase.containsKey(id);
    }

    @Override
    public boolean isExistsById(long id) {
        log.debug("Trying to check if exists trainee with id=" + id);
        return traineeDataBase.containsKey(id);
    }

    @Override
    public boolean isUserNameExists(String username) {
        log.debug("Trying to check if trainee with username=" + username + " is existed");
        return traineeDataBase.values()
                .stream()
                .map(User::getUsername)
                .anyMatch(un -> un.equals(username));
    }
}
