package ru.course.aston.service.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import ru.course.aston.Main;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.repository.FractionRepository;
import ru.course.aston.service.FractionService;
import ru.course.aston.servlet.dto.FractionDTO;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

//@Testcontainers
class FractionServiceImplTest {
    @Container
    public static final GenericContainer<?> container = new PostgreSQLContainer<>("postgres:14-alpine")
//         .withCommand("docker-compose up docker run -p 8080:8080 tryrestagain");
            .withInitScript("sql/schema.sql");

    @BeforeAll
    public static void startContainer() {
        System.out.println("Старт контейнера");
        container.start();

    }

    @Test
    void findById() {
        FractionService fractionService = new FractionServiceImpl();
        FractionDTO fractionDTO = fractionService.findById(1L);
        assertEquals(fractionDTO.getFractionId(), 1L);
    }

    @Test
    void deleteById() {
        FractionDTO fractionDTO = new FractionDTO(1L, "newFraction");
        FractionService fractionService = new FractionServiceImpl();
        Long id = fractionService.save(fractionDTO).getFractionId();
        fractionService.deleteById(id);
        assertEquals(fractionService.findById(id), null);

    }

    @Test
    void save() {
        FractionDTO fractionDTO = new FractionDTO(1L, "newFraction");
        FractionService fractionService = new FractionServiceImpl();
        Long id = fractionService.save(fractionDTO).getFractionId();
        assertEquals(fractionService.findById(id).getFractionName(), "newFraction");
        fractionService.deleteById(id);


    }

    @Test
    void findAll() {
        FractionService fractionService = new FractionServiceImpl();
        assertEquals(fractionService.findAll().size(), 2);
    }

    @Test
    void update() {
        FractionDTO fractionDTO = new FractionDTO(1L, "newFraction");
        FractionService fractionService = new FractionServiceImpl();
        fractionService.update(fractionDTO);
        assertEquals(fractionDTO.getFractionId(), 1L);
        fractionDTO.setFractionName("Aльянс");
        fractionService.update(fractionDTO);
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}