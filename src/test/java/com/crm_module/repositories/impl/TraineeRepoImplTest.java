package com.crm_module.repositories.impl;

import com.crm_module.UnitTestBase;
import com.crm_module.models.users.Trainee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TraineeRepoImplTest extends UnitTestBase {
    @Mock
    private Map<Long, Trainee> mockDatabase;

    @InjectMocks
    private TraineeRepoImpl traineeRepo;

    private Trainee testTrainee;

    @BeforeEach
    void setUp() {
        testTrainee = Trainee.builder()
                .userId(1L)
                .dateOfBirth("01.01.2000")
                .username("testUsername")
                .address("Some street")
                .build();
    }

    @AfterEach
    void destroy() {
        testTrainee = null;
    }

    @Test
    @DisplayName("generateId should generate ids sequentially")
    void generateId_shouldReturnSequentialIds() {
        // Given
        long firstExpectedId = 1;
        long secondExpectedId = 2;
        long thirdExpectedId = 3;

        // When
        long firstGeneratedId = traineeRepo.generateId();
        long secondGeneratedId = traineeRepo.generateId();
        long thirdGeneratedId = traineeRepo.generateId();

        // Then
        assertEquals(firstExpectedId, firstGeneratedId);
        assertEquals(secondExpectedId, secondGeneratedId);
        assertEquals(thirdExpectedId, thirdGeneratedId);
    }

    @Test
    @DisplayName("Should return Trainee when it exists")
    void findById_ShouldReturnTrainee_WhenExists() {
        // Given
        when(mockDatabase.get(anyLong())).thenReturn(testTrainee);

        // When
        var result = traineeRepo.findById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(testTrainee, result.get());

        verify(mockDatabase, times(1)).get(idArgumentCaptor.capture());
    }

    @Test
    @DisplayName("Should return empty Optional when Trainee does not exist")
    void findById_ShouldReturnEmptyOptional_WhenNotExists() {
        // Given
        when(mockDatabase.get(anyLong())).thenReturn(null);

        // When
        var result = traineeRepo.findById(1L);

        // Then
        assertFalse(result.isPresent());
        verify(mockDatabase, times(1)).get(idArgumentCaptor.capture());
    }

    @Test
    @DisplayName("Should save Trainee to the database")
    void save_ShouldSaveTrainee() {
        // Given
        when(mockDatabase.put(anyLong(), any(Trainee.class))).thenReturn(testTrainee);

        // When
        var result = traineeRepo.save(new Trainee());

        // Then
        assertNotNull(result);
        verify(mockDatabase, times(1)).put(
                idArgumentCaptor.capture(),
                traineeArgumentCaptor.capture()
        );
    }

    @Test
    @DisplayName("Should update Trainee in the database")
    void update_ShouldUpdateTrainee() {
        // Given
        when(mockDatabase.put(anyLong(), any(Trainee.class))).thenReturn(testTrainee);

        // When
        Trainee result = traineeRepo.update(new Trainee());

        // Then
        assertEquals(testTrainee, result);
        verify(mockDatabase, times(1)).put(
                idArgumentCaptor.capture(),
                traineeArgumentCaptor.capture()
        );
    }

    @Test
    @DisplayName("Should remove Trainee and return true when successful")
    void deleteById_ShouldRemoveTraineeAndReturnTrue_WhenSuccessful() {
        // Given
        when(mockDatabase.containsKey(anyLong())).thenReturn(false);

        // When
        boolean result = traineeRepo.deleteById(1L);

        // Then
        assertTrue(result);
        verify(mockDatabase, times(1)).remove(idArgumentCaptor.capture());
        verify(mockDatabase, times(1)).containsKey(idArgumentCaptor.capture());
    }

    @Test
    @DisplayName("Should return true when Trainee exists by ID")
    void isExistsById_ShouldReturnTrue_WhenTraineeExists() {
        // Given
        when(mockDatabase.containsKey(anyLong())).thenReturn(true);

        // When
        boolean result = traineeRepo.isExistsById(1L);

        // Then
        assertTrue(result);
        verify(mockDatabase, times(1)).containsKey(idArgumentCaptor.capture());
    }

    @Test
    @DisplayName("Should return false when Trainee does not exist by ID")
    void isExistsById_ShouldReturnFalse_WhenTraineeNotExists() {
        // Give
        when(mockDatabase.containsKey(anyLong())).thenReturn(false);

        // When
        boolean result = traineeRepo.isExistsById(1L);

        // Then
        assertFalse(result);
        verify(mockDatabase, times(1)).containsKey(idArgumentCaptor.capture());
    }

    @Test
    @DisplayName("Should return true when username exists")
    void isUserNameExists_ShouldReturnTrue_WhenUsernameExists() {
        // Given
        when(mockDatabase.values()).thenReturn(List.of(testTrainee));

        // When
        boolean result = traineeRepo.isUserNameExists("testUsername");

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
        boolean result = traineeRepo.isUserNameExists("testUsername");

        // Then
        assertFalse(result);
        verify(mockDatabase, times(1)).values();
    }
}