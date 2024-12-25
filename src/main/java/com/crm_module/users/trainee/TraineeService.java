package com.crm_module.users.trainee;

import org.springframework.stereotype.Service;

@Service
public interface TraineeService {
    Trainee findById(long id);

    Trainee save(Trainee trainee);

    Trainee update(Trainee trainee);

    boolean deleteById(long id);

    boolean isExistsById(long id);
}
