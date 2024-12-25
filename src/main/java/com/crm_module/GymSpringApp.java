package com.crm_module;

import com.crm_module.app_config.AppConfig;
import com.crm_module.training.Training;
import com.crm_module.users.trainee.Trainee;
import com.crm_module.users.trainer.Trainer;
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

        System.out.println("Trainees: " + traineeDataBase);
        System.out.println("Trainers: " + trainerDataBase);
        System.out.println("Trainings: " + trainingDataBase);
    }
}
