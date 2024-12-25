package com.crm_module.users.trainee;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraineeRepo {
    Optional<Trainee> findById(long id);

    Trainee save(Trainee trainee);

    Trainee update(Trainee trainee);

    boolean deleteById(long id);

    boolean isExistsById(long id);

    boolean isUserNameExists(String username);
}
