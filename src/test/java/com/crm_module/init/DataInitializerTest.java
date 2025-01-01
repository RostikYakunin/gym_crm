package com.crm_module.init;

import com.crm_module.UnitTestBase;
import com.crm_module.models.training.Training;
import com.crm_module.models.users.Trainee;
import com.crm_module.models.users.Trainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class DataInitializerTest extends UnitTestBase {
    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Map<Long, Trainee> traineeDataBase;

    @Mock
    private Map<Long, Trainer> trainerDataBase;

    @Mock
    private Map<Long, Training> trainingDataBase;

    @InjectMocks
    private DataInitializer dataInitializer;

    @Test
    @DisplayName("Should throw exception when file not found")
    void initializeData_shouldThrowExceptionWhenFileNotFound() throws Exception {
        // Given
        ReflectionTestUtils.setField(dataInitializer, "traineeDataFilePath", "mock/path/to/trainee_data.json");
        when(objectMapper.readValue(any(File.class), eq(Trainee[].class))).thenThrow(FileNotFoundException.class);

        // When - Then
        assertThrows(
                RuntimeException.class,
                dataInitializer::initializeData,
                "Something went wrong with file deserialization"
        );
    }
}