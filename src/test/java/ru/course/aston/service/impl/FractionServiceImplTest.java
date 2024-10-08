package ru.course.aston.service.impl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import ru.course.aston.Main;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.repository.FractionRepository;
import ru.course.aston.repository.impl.FractionRepositoryImpl;
import ru.course.aston.service.FractionService;
import ru.course.aston.servlet.dto.FractionDTO;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

//@Testcontainers
class FractionServiceImplTest {
    static FractionService fractionService;
    static ConnectionManager connectionManager;
    private static JdbcDatabaseDelegate jdbcDatabaseDelegate;
    @Container
    public static final PostgreSQLContainer container = new PostgreSQLContainer<>("postgres:14-alpine");

    @BeforeAll
    public static void startContainer() {
        container.start();
        connectionManager = new ConnectionManagerImpl();
        connectionManager.setDriver(container.getDriverClassName());
        connectionManager.setJdbcUrl(container.getJdbcUrl());
        connectionManager.setUsername(container.getUsername());
        connectionManager.setPassword(container.getPassword());
        jdbcDatabaseDelegate = new JdbcDatabaseDelegate(container, "");
        fractionService = new FractionServiceImpl(connectionManager);
    }

    @BeforeEach
    public void initSchema() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "sql/schema.sql");

    }

    @Test
    void findById() {
        FractionDTO fractionDTO = fractionService.findById(1L);
        assertEquals(fractionDTO.getFractionId(), 1L);
    }

    @Test
    void deleteById() {
        FractionDTO fractionDTO = new FractionDTO(1L, "newFraction");
        Long id = fractionService.save(fractionDTO).getFractionId();
        fractionService.deleteById(id);
        assertEquals(fractionService.findById(id), null);

    }

    @Test
    void save() {
        FractionDTO fractionDTO = new FractionDTO(1L, "newFraction");
        Long id = fractionService.save(fractionDTO).getFractionId();
        assertEquals(fractionService.findById(id).getFractionName(), "newFraction");
        fractionService.deleteById(id);


    }

    @Test
    void findAll() {
        assertEquals(fractionService.findAll().size(), 2);
    }

    @Test
    void update() {
        FractionDTO fractionDTO = new FractionDTO(1L, "newFraction");
        fractionService.update(fractionDTO);
        assertEquals(fractionDTO.getFractionId(), 1L);
        fractionDTO.setFractionName("Aльянс");
        fractionService.update(fractionDTO);
    }

    @Test
    void constructor() {
        FractionServiceImpl fractionServiceImpl = new FractionServiceImpl();
        assertNotNull(fractionServiceImpl);
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}