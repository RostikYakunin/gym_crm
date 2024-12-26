package com.crm_module;

import com.crm_module.config.AppConfig;
import com.crm_module.models.training.Training;
import com.crm_module.models.users.impl.Trainee;
import com.crm_module.models.users.impl.Trainer;
import com.crm_module.repositories.trainee_repo.TraineeRepo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class GymSpringApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Map<Long, Trainee> traineeDataBase = context.getBean("traineeDataBase", HashMap.class);
        Map<Long, Trainer> trainerDataBase = context.getBean("trainerDataBase", HashMap.class);
        Map<Long, Training> trainingDataBase = context.getBean("trainingDataBase", HashMap.class);

        TraineeRepo trainingRepoImpl = context.getBean("traineeRepoImpl", TraineeRepo.class);
        System.out.println(trainingRepoImpl.findById(1));

        System.out.println("Trainees: " + traineeDataBase);
        System.out.println("Trainers: " + trainerDataBase);
        System.out.println("Trainings: " + trainingDataBase);
    }
}
