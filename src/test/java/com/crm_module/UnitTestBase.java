package com.crm_module;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public abstract class UnitTestBase {
    private AutoCloseable openMocks;

    @BeforeEach
    void initMocks() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeMocks() throws Exception {
        openMocks.close();
    }
}
