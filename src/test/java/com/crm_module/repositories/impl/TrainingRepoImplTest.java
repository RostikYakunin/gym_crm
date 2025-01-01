package com.crm_module.repositories.impl;

import com.crm_module.UnitTestBase;
import com.crm_module.models.training.Training;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainingRepoImplTest extends UnitTestBase {
    @Mock
    private Map<Long, Training> mockDatabase;

    @InjectMocks
    private TrainingRepoImpl trainingRepo;

    private Training testTraining;

    @BeforeEach
    void setUp() {
        testTraining = Training.builder()
                .id(1L)
                .build();
    }

    @AfterEach
    void tearDown() {
        testTraining = null;
    }

    @Test
    @DisplayName("generateId should generate ids sequentially")
    void generateId_shouldReturnSequentialIds() {
        // Given
        long firstExpectedId = 1;
        long secondExpectedId = 2;
        long thirdExpectedId = 3;

        // When
        long firstGeneratedId = trainingRepo.generateId();
        long secondGeneratedId = trainingRepo.generateId();
        long thirdGeneratedId = trainingRepo.generateId();

        // Then
        assertEquals(firstExpectedId, firstGeneratedId);
        assertEquals(secondExpectedId, secondGeneratedId);
        assertEquals(thirdExpectedId, thirdGeneratedId);
    }

    @Test
    @DisplayName("findById should return training when exists")
    void findById_ShouldReturnTraining_WhenExists() {
        // Given
        when(mockDatabase.get(anyLong())).thenReturn(testTraining);

        // When
        var result = trainingRepo.findById(testTraining.getId());

        // Then
        assertTrue(result.isPresent());
        assertEquals(testTraining, result.get());
        verify(mockDatabase, times(1)).get(idArgumentCaptor.capture());
    }

    @Test
    @DisplayName("findById should return empty optional when training does not exist")
    void findById_ShouldReturnEmptyOptional_WhenNotExists() {
        // Given
        when(mockDatabase.get(anyLong())).thenReturn(null);

        // When
        var result = trainingRepo.findById(testTraining.getId());

        // Then
        assertFalse(result.isPresent());
        verify(mockDatabase, times(1)).get(idArgumentCaptor.capture());
    }

    @Test
    @DisplayName("save should save the training")
    void save_ShouldSaveTraining() {
        // Given
        when(mockDatabase.put(anyLong(), any(Training.class))).thenReturn(null);

        // When
        var result = trainingRepo.save(testTraining);

        // Then
        assertNull(result);
        verify(mockDatabase, times(1)).put(
                idArgumentCaptor.capture(),
                trainingArgumentCaptor.capture()
        );
    }

    @Test
    @DisplayName("update should update the training")
    void update_ShouldUpdateTraining() {
        // Given
        when(mockDatabase.put(anyLong(), any(Training.class))).thenReturn(testTraining);

        // When
        var result = trainingRepo.update(testTraining);

        // Then
        assertEquals(testTraining, result);
        verify(mockDatabase, times(1)).put(
                idArgumentCaptor.capture(),
                trainingArgumentCaptor.capture()
        );
    }

    @Test
    @DisplayName("deleteById should remove training and return true when successful")
    void deleteById_ShouldRemoveTrainingAndReturnTrue_WhenSuccessful() {
        // Given
        when(mockDatabase.containsKey(anyLong())).thenReturn(false);

        // When
        var result = trainingRepo.deleteById(testTraining.getId());

        // Then
        assertTrue(result);
        verify(mockDatabase, times(1)).remove(idArgumentCaptor.capture());
        verify(mockDatabase, times(1)).containsKey(idArgumentCaptor.capture());
    }

    @Test
    @DisplayName("deleteById should return false when training still exists")
    void deleteById_ShouldReturnFalse_WhenTrainingStillExists() {
        // Given
        when(mockDatabase.containsKey(anyLong())).thenReturn(true);

        // When
        var result = trainingRepo.deleteById(testTraining.getId());

        // Then
        assertFalse(result);
        verify(mockDatabase, times(1)).remove(idArgumentCaptor.capture());
        verify(mockDatabase, times(1)).containsKey(idArgumentCaptor.capture());
    }

    @Test
    @DisplayName("isExistsById should return true when training exists")
    void isExistsById_ShouldReturnTrue_WhenTrainingExists() {
        // Given
        when(mockDatabase.containsKey(anyLong())).thenReturn(true);

        // When
        var result = trainingRepo.isExistsById(testTraining.getId());

        // Then
        assertTrue(result);
        verify(mockDatabase, times(1)).containsKey(idArgumentCaptor.capture());
    }

    @Test
    @DisplayName("isExistsById should return false when training does not exist")
    void isExistsById_ShouldReturnFalse_WhenTrainingNotExists() {
        // Given
        when(mockDatabase.containsKey(anyLong())).thenReturn(false);

        // When
        var result = trainingRepo.isExistsById(testTraining.getId());

        // Then
        assertFalse(result);
        verify(mockDatabase, times(1)).containsKey(idArgumentCaptor.capture());
    }
}