package com.crm_module.mapper;

import com.crm_module.models.users.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class TraineeMapperTest {
    private TraineeMapper traineeMapper;

    @BeforeEach
    void setUp() {
        traineeMapper = Mappers.getMapper(TraineeMapper.class);
    }

    @Test
    @DisplayName("Should be updated only non null fields when exists updated fields")
    void givenUpdatedTrainee_whenUpdateTrainee_thenOnlyNonNullFieldsShouldBeUpdated() {
        // Given
        var existingTrainee = Trainee.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .password("12345")
                .isActive(true)
                .build();

        var updatedTrainee = Trainee.builder()
                .firstName("Jane")
                .username("janedoe")
                .build();

        // When
        traineeMapper.updateTrainee(existingTrainee, updatedTrainee);

        // Then
        assertEquals("Jane", existingTrainee.getFirstName());
        assertEquals("Doe", existingTrainee.getLastName());
        assertEquals("janedoe", existingTrainee.getUsername());
        assertEquals("12345", existingTrainee.getPassword());
    }

    @Test
    @DisplayName("Should not update any fields when all fields are null")
    void givenNullUpdatedTrainee_whenUpdateTrainee_thenNoFieldsShouldBeUpdated() {
        // Given
        var existingTrainee = Trainee.builder()
                .firstName("John")
                .lastName("Doe")
                .username("johndoe")
                .password("12345")
                .isActive(true)
                .build();

        var updatedTrainee = new Trainee();

        // When
        traineeMapper.updateTrainee(existingTrainee, updatedTrainee);

        // Then
        assertEquals("John", existingTrainee.getFirstName());
        assertEquals("Doe", existingTrainee.getLastName());
        assertEquals("johndoe", existingTrainee.getUsername());
        assertEquals("12345", existingTrainee.getPassword());
    }
}