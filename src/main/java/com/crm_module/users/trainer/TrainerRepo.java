package com.crm_module.users.trainer;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepo {
    Optional<Trainer> findById(long id);
    Trainer save (Trainer trainer);
    Trainer update (Trainer trainer);
    boolean deleteById (long id);
    boolean isExistsById(long id);
    boolean isUserNameExists(String username);
}
