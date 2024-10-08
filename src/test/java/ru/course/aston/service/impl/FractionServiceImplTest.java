package ru.course.aston.service.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.service.FractionService;
import ru.course.aston.servlet.dto.FractionDTO;

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
        assertEquals(1L,fractionDTO.getFractionId());
    }

    @Test
    void deleteById() {
        FractionDTO fractionDTO = new FractionDTO(1L, "newFraction");
        Long id = fractionService.save(fractionDTO).getFractionId();
        fractionService.deleteById(id);
        assertNull(fractionService.findById(id));

    }

    @Test
    void save() {
        FractionDTO fractionDTO = new FractionDTO(1L, "newFraction");
        Long id = fractionService.save(fractionDTO).getFractionId();
        assertEquals("newFraction",fractionService.findById(id).getFractionName() );
        fractionService.deleteById(id);


    }

    @Test
    void findAll() {
        assertEquals(2,fractionService.findAll().size() );
    }

    @Test
    void update() {
        FractionDTO fractionDTO = new FractionDTO(1L, "newFraction");
        fractionService.update(fractionDTO);
        assertEquals(1L,fractionDTO.getFractionId());
        fractionDTO.setFractionName("Aльянс");
        fractionService.update(fractionDTO);
    }

    @Test
    void constructor() {
        FractionService service = new FractionServiceImpl();
        assertNotNull(service);
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}