package com.crm_module.repositories.trainee_repo;

import com.crm_module.models.users.impl.Trainee;
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
