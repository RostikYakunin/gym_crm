package com.crm_module.repositories.trainee_repo.impl;

import com.crm_module.UnitTestBase;
import com.crm_module.models.users.impl.Trainee;
import com.crm_module.repositories.trainee_repo.TraineeRepo;
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

class TraineeRepoImplTest extends UnitTestBase {
    @Mock
    private Map<Long, Trainee> mockDatabase;

    @InjectMocks
    private TraineeRepo traineeRepo;

    private Trainee testTrainee;

    @BeforeEach
    void setUp() {
        testTrainee = Trainee.builder()
                .userId(1L)
                .username("testUsername")
                .build();
    }

    @AfterEach
    void destroy() {
        testTrainee = null;
    }

    @Test
    @DisplayName("Should return Trainee when it exists")
    void findById_ShouldReturnTrainee_WhenExists() {
        // Given
        when(mockDatabase.get(testTrainee.getUserId())).thenReturn(testTrainee);

        // When
        Optional<Trainee> result = traineeRepo.findById(testTrainee.getUserId());

        // Then
        assertTrue(result.isPresent());
        assertEquals(testTrainee, result.get());
        verify(mockDatabase, times(1)).get(testTrainee.getUserId());
    }

    @Test
    @DisplayName("Should return empty Optional when Trainee does not exist")
    void findById_ShouldReturnEmptyOptional_WhenNotExists() {
        // Given
        when(mockDatabase.get(testTrainee.getUserId())).thenReturn(null);

        // When
        Optional<Trainee> result = traineeRepo.findById(testTrainee.getUserId());

        // Then
        assertFalse(result.isPresent());
        verify(mockDatabase, times(1)).get(testTrainee.getUserId());
    }

    @Test
    @DisplayName("Should save Trainee to the database")
    void save_ShouldSaveTrainee() {
        // Given
        when(mockDatabase.put(testTrainee.getUserId(), testTrainee)).thenReturn(testTrainee);

        // When
        Trainee result = traineeRepo.save(testTrainee);

        // Then
        assertNotNull(result);
        verify(mockDatabase, times(1)).put(testTrainee.getUserId(), testTrainee);
    }

    @Test
    @DisplayName("Should update Trainee in the database")
    void update_ShouldUpdateTrainee() {
        // Given
        when(mockDatabase.put(testTrainee.getUserId(), testTrainee)).thenReturn(testTrainee);

        // When
        Trainee result = traineeRepo.update(testTrainee);

        // Then
        assertEquals(testTrainee, result);
        verify(mockDatabase, times(1)).put(testTrainee.getUserId(), testTrainee);
    }

    @Test
    @DisplayName("Should remove Trainee and return true when successful")
    void deleteById_ShouldRemoveTraineeAndReturnTrue_WhenSuccessful() {
        // Given
        when(mockDatabase.containsKey(testTrainee.getUserId())).thenReturn(false);

        // When
        boolean result = traineeRepo.deleteById(testTrainee.getUserId());

        // Then
        assertTrue(result);
        verify(mockDatabase, times(1)).remove(testTrainee.getUserId());
        verify(mockDatabase, times(1)).containsKey(testTrainee.getUserId());
    }

    @Test
    @DisplayName("Should return true when Trainee exists by ID")
    void isExistsById_ShouldReturnTrue_WhenTraineeExists() {
        // Given
        when(mockDatabase.containsKey(testTrainee.getUserId())).thenReturn(true);

        // When
        boolean result = traineeRepo.isExistsById(testTrainee.getUserId());

        // Then
        assertTrue(result);
        verify(mockDatabase, times(1)).containsKey(testTrainee.getUserId());
    }

    @Test
    @DisplayName("Should return false when Trainee does not exist by ID")
    void isExistsById_ShouldReturnFalse_WhenTraineeNotExists() {
        // Give
        when(mockDatabase.containsKey(testTrainee.getUserId())).thenReturn(false);

        // When
        boolean result = traineeRepo.isExistsById(testTrainee.getUserId());

        // Then
        assertFalse(result);
        verify(mockDatabase, times(1)).containsKey(testTrainee.getUserId());
    }

    @Test
    @DisplayName("Should return true when username exists")
    void isUserNameExists_ShouldReturnTrue_WhenUsernameExists() {
        // Given
        when(mockDatabase.values()).thenReturn(List.of(testTrainee));

        // When
        boolean result = traineeRepo.isUserNameExists(testTrainee.getUsername());

        // Then
        assertTrue(result);
        verify(mockDatabase, times(1)).values();
    }

    @Test
    @DisplayName("Should return false when username does not exist")
    void isUserNameExists_ShouldReturnFalse_WhenUsernameNotExists() {
        // Given
        when(mockDatabase.values()).thenReturn(Collections.emptyList());

        // When
        boolean result = traineeRepo.isUserNameExists(testTrainee.getUsername());

        // Then
        assertFalse(result);
        verify(mockDatabase, times(1)).values();
    }
}