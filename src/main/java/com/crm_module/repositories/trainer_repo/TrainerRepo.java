package com.crm_module.repositories.trainer_repo;

import com.crm_module.models.users.impl.Trainer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepo {
    Optional<Trainer> findById(long id);

    Trainer save(Trainer trainer);

    Trainer update(Trainer trainer);

    boolean deleteById(long id);

    boolean isExistsById(long id);

    boolean isUserNameExists(String username);
}
