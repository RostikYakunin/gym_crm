package com.crm_module.repositories.trainer_repo.impl;

import com.crm_module.UnitTestBase;
import com.crm_module.models.users.impl.Trainer;
import com.crm_module.repositories.trainer_repo.TrainerRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainerRepoImplTest extends UnitTestBase {
    @Mock
    private Map<Long, Trainer> mockDatabase;

    @InjectMocks
    private TrainerRepo trainerRepo;

    private Trainer testTrainer;

    @BeforeEach
    void setUp() {
        testTrainer = Trainer.builder()
                .userId(1L)
                .username("testTrainer")
                .build();
    }

    @AfterEach
    void tearDown() {
        testTrainer = null;
    }

    @Test
    @DisplayName("findById should return trainer when exists")
    void findById_ShouldReturnTrainer_WhenExists() {
        // Given
        when(mockDatabase.get(testTrainer.getUserId())).thenReturn(testTrainer);

        // When
        Optional<Trainer> result = trainerRepo.findById(testTrainer.getUserId());

        // Then
        assertTrue(result.isPresent());
        assertEquals(testTrainer, result.get());
        verify(mockDatabase, times(1)).get(testTrainer.getUserId());
    }

    @Test
    @DisplayName("findById should return empty optional when trainer does not exist")
    void findById_ShouldReturnEmptyOptional_WhenNotExists() {
        // Given
        when(mockDatabase.get(testTrainer.getUserId())).thenReturn(null);

        // When
        Optional<Trainer> result = trainerRepo.findById(testTrainer.getUserId());

        // Then
        assertFalse(result.isPresent());
        verify(mockDatabase, times(1)).get(testTrainer.getUserId());
    }

    @Test
    @DisplayName("save should save the trainer")
    void save_ShouldSaveTrainer() {
        // Given
        when(mockDatabase.put(testTrainer.getUserId(), testTrainer)).thenReturn(null);

        // When
        Trainer result = trainerRepo.save(testTrainer);

        // Then
        assertNull(result);
        verify(mockDatabase, times(1)).put(testTrainer.getUserId(), testTrainer);
    }

    @Test
    @DisplayName("update should update the trainer")
    void update_ShouldUpdateTrainer() {
        // Given
        when(mockDatabase.put(testTrainer.getUserId(), testTrainer)).thenReturn(testTrainer);

        // When
        Trainer result = trainerRepo.update(testTrainer);

        // Then
        assertEquals(testTrainer, result);
        verify(mockDatabase, times(1)).put(testTrainer.getUserId(), testTrainer);
    }

    @Test
    @DisplayName("deleteById should remove trainer and return true when successful")
    void deleteById_ShouldRemoveTrainerAndReturnTrue_WhenSuccessful() {
        // Given
        when(mockDatabase.containsKey(testTrainer.getUserId())).thenReturn(false);

        // When
        boolean result = trainerRepo.deleteById(testTrainer.getUserId());

        // Then
        assertTrue(result);
        verify(mockDatabase, times(1)).remove(testTrainer.getUserId());
        verify(mockDatabase, times(1)).containsKey(testTrainer.getUserId());
    }

    @Test
    @DisplayName("isExistsById should return true when trainer exists")
    void isExistsById_ShouldReturnTrue_WhenTrainerExists() {
        // Given
        when(mockDatabase.containsKey(testTrainer.getUserId())).thenReturn(true);

        // When
        boolean result = trainerRepo.isExistsById(testTrainer.getUserId());

        // Then
        assertTrue(result);
        verify(mockDatabase, times(1)).containsKey(testTrainer.getUserId());
    }

    @Test
    @DisplayName("isExistsById should return false when trainer does not exist")
    void isExistsById_ShouldReturnFalse_WhenTrainerNotExists() {
        // Given
        when(mockDatabase.containsKey(testTrainer.getUserId())).thenReturn(false);

        // When
        boolean result = trainerRepo.isExistsById(testTrainer.getUserId());

        // Then
        assertFalse(result);
        verify(mockDatabase, times(1)).containsKey(testTrainer.getUserId());
    }

    @Test
    @DisplayName("isUserNameExists should return true when username exists")
    void isUserNameExists_ShouldReturnTrue_WhenUsernameExists() {
        // Given
        when(mockDatabase.values()).thenReturn(List.of(testTrainer));

        // When
        boolean result = trainerRepo.isUserNameExists(testTrainer.getUsername());

        // Then
        assertTrue(result);
        verify(mockDatabase, times(1)).values();
    }

    @Test
    @DisplayName("isUserNameExists should return false when username does not exist")
    void isUserNameExists_ShouldReturnFalse_WhenUsernameNotExists() {
        // Given
        when(mockDatabase.values()).thenReturn(Collections.emptyList());

        // When
        boolean result = trainerRepo.isUserNameExists(testTrainer.getUsername());

        // Then
        assertFalse(result);
        verify(mockDatabase, times(1)).values();
    }

}