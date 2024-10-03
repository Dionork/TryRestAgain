package ru.course.aston.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.lifecycle.Startables;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FractionServiceImplTest {
    @Container
    private static final GenericContainer container = new PostgreSQLContainer("postgres:14-alpine")
            .withInitScript("schema.sql");

    @BeforeAll
    public static void startContainer() {
        Startables.deepStart(container);
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void save() {
        FractionServiceImpl fractionServiceImpl = Mockito.mock(FractionServiceImpl.class);
        Mockito.when(fractionServiceImpl.save(Mockito.any())).thenReturn(null);
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }
}