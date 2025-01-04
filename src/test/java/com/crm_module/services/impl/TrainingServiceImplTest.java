package com.crm_module.services.impl;

import com.crm_module.UnitTestBase;
import com.crm_module.models.training.Training;
import com.crm_module.repositories.TrainingRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingServiceImplTest extends UnitTestBase {
    @Mock
    private TrainingRepo trainingRepo;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    private Training testTraining;

    @BeforeEach
    void setUp() {
        testTraining = Training.builder()
                .id(1L)
                .build();
    }

    @AfterEach
    void destroy() {
        testTraining = null;
    }

    @Test
    @DisplayName("findById should return training when exists")
    void findById_ShouldReturnTraining_WhenExists() {
        // Given
        when(trainingRepo.findById(testTraining.getId())).thenReturn(Optional.of(testTraining));

        // When
        Training result = trainingService.findById(testTraining.getId());

        // Then
        assertEquals(testTraining, result);
        verify(trainingRepo, times(1)).findById(testTraining.getId());
    }

    @Test
    @DisplayName("save should persist training and return it")
    void save_ShouldPersistTrainingAndReturnIt() {
        // Given
        when(trainingRepo.save(testTraining)).thenReturn(testTraining);

        // When
        Training result = trainingService.save(testTraining);

        // Then
        assertEquals(testTraining, result);
        verify(trainingRepo, times(1)).save(testTraining);
    }

    @Test
    @DisplayName("isExistsById should return true when training exists")
    void isExistsById_ShouldReturnTrue_WhenExists() {
        // Given
        when(trainingRepo.isExistsById(testTraining.getId())).thenReturn(true);

        // When
        boolean result = trainingService.isExistsById(testTraining.getId());

        // Then
        assertTrue(result);
        verify(trainingRepo, times(1)).isExistsById(testTraining.getId());
    }

    @Test
    @DisplayName("isExistsById should return false when training does not exist")
    void isExistsById_ShouldReturnFalse_WhenNotExists() {
        // Given
        when(trainingRepo.isExistsById(testTraining.getId())).thenReturn(false);

        // When
        boolean result = trainingService.isExistsById(testTraining.getId());

        // Then
        assertFalse(result);
        verify(trainingRepo, times(1)).isExistsById(testTraining.getId());
    }

}