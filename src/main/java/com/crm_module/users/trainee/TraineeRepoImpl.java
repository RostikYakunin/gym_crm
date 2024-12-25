package com.crm_module.users.trainee;

import com.crm_module.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TraineeRepoImpl implements TraineeRepo {
    private final Map<Long, Trainee> traineeDataBase;

    @Override
    public Optional<Trainee> findById(long id) {
        return Optional.ofNullable(traineeDataBase.get(id));
    }

    @Override
    public Trainee save(Trainee trainee) {
        return traineeDataBase.put(trainee.getUserId(), trainee);
    }

    @Override
    public Trainee update(Trainee trainee) {
        return save(trainee);
    }

    @Override
    public boolean deleteById(long id) {
        traineeDataBase.remove(id);
        return traineeDataBase.containsKey(id);
    }

    @Override
    public boolean isExistsById(long id) {
        return traineeDataBase.containsKey(id);
    }

    @Override
    public boolean isUserNameExists(String username) {
        return traineeDataBase.values()
                .stream()
                .map(User::getUsername)
                .anyMatch( un -> un.equals(username));
    }
}
