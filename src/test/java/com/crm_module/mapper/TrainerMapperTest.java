package com.crm_module.mapper;

import com.crm_module.models.users.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class TrainerMapperTest {
    private TrainerMapper trainerMapper;

    @BeforeEach
    void setUp() {
        trainerMapper = Mappers.getMapper(TrainerMapper.class);
    }

    @Test
    @DisplayName("Should be updated only non null fields when exists updated fields")
    void givenUpdatedTrainer_whenUpdateTrainer_thenOnlyNonNullFieldsShouldBeUpdated() {
        // Given
        var existingTrainer = Trainer.builder()
                .firstName("Alice")
                .lastName("Smith")
                .username("alicesmith")
                .password("password")
                .isActive(true)
                .build();

        var updatedTrainer = Trainer.builder()
                .lastName("Johnson")
                .isActive(false)
                .build();

        // When
        trainerMapper.updateTrainer(existingTrainer, updatedTrainer);

        // Then
        assertEquals("Alice", existingTrainer.getFirstName());
        assertEquals("Johnson", existingTrainer.getLastName());
        assertEquals("alicesmith", existingTrainer.getUsername());
        assertEquals("password", existingTrainer.getPassword());
        assertFalse(existingTrainer.isActive());
    }

    @Test
    @DisplayName("Should not update any fields when all fields are null")
    void givenNullUpdatedTrainer_whenUpdateTrainer_thenNoFieldsShouldBeUpdated() {
        // Given
        var existingTrainer = Trainer.builder()
                .firstName("Alice")
                .lastName("Smith")
                .username("alicesmith")
                .password("password")
                .isActive(true)
                .build();

        var updatedTrainer = Trainer.builder().build();

        // When
        trainerMapper.updateTrainer(existingTrainer, updatedTrainer);

        System.out.println(existingTrainer);

        // Then
        assertEquals("Alice", existingTrainer.getFirstName());
        assertEquals("Smith", existingTrainer.getLastName());
        assertEquals("alicesmith", existingTrainer.getUsername());
        assertEquals("password", existingTrainer.getPassword());
    }
}