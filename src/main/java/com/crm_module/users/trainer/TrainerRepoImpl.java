package com.crm_module.users.trainer;


import com.crm_module.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TrainerRepoImpl implements TrainerRepo {
    private final Map<Long, Trainer> trainerDataBase;

    @Override
    public Optional<Trainer> findById(long id) {
        return Optional.ofNullable(trainerDataBase.get(id));
    }

    @Override
    public Trainer save(Trainer trainer) {
        return trainerDataBase.put(trainer.getUserId(), trainer);
    }

    @Override
    public Trainer update(Trainer trainer) {
        return trainerDataBase.put(trainer.getUserId(), trainer);
    }

    @Override
    public boolean deleteById(long id) {
        trainerDataBase.remove(id);
        return trainerDataBase.containsKey(id);
    }

    @Override
    public boolean isExistsById(long id) {
        return trainerDataBase.containsKey(id);
    }

    @Override
    public boolean isUserNameExists(String username) {
        return trainerDataBase.values()
                .stream()
                .map(User::getUsername)
                .anyMatch( un -> un.equals(username));
    }
}
