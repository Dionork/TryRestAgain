package ru.course.aston.service.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.model.Hero;
import ru.course.aston.model.HeroToFraction;
import ru.course.aston.repository.FractionRepository;
import ru.course.aston.repository.impl.FractionRepositoryImpl;
import ru.course.aston.service.HeroToFractionService;
import ru.course.aston.servlet.dto.HeroToFractionDTO;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class HeroToFractionServiceImplTest {
    static HeroToFractionService service;
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
        service = new HeroToFractionServiceImpl(connectionManager);
    }

    @BeforeEach
    public void initSchema() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "sql/schema.sql");

    }

    @Test
    void findById() {
        HeroToFractionDTO heroToFractionDTO = service.findById(1L);
        assertEquals(1L, heroToFractionDTO.getHeroToFractionId());
    }

    @Test
    void deleteById() {
        Hero hero = new Hero(4L, "Артас", "HeroLastName", 1L);
        Fraction fraction = new Fraction(2L, "Орда");
        HeroToFractionDTO heroToFractionDTOd = new HeroToFractionDTO(1L,
                hero
                , fraction);
        Long id = service.save(heroToFractionDTOd).getHeroToFractionId();
        service.deleteById(id);
        assertNull(service.findById(id));
    }

    @Test
    void save() {
        Hero hero = new Hero(4L, "Артас", "HeroLastName", 1L);
        Fraction fraction = new Fraction(2L, "Орда");
        HeroToFractionDTO heroToFractionDTOd = new HeroToFractionDTO(1L,
                hero
                , fraction);
        Long id = service.save(heroToFractionDTOd).getHeroToFractionId();
        assertEquals(heroToFractionDTOd.getHeroToFractionId(), 1L);
        service.deleteById(id);
    }

    @Test
    void findAll() {
        assertEquals(7, service.findAll().size());
    }

    @Test
    void update() {
        Hero hero = new Hero(4L, "Артас", "HeroLastName", 1L);
        Fraction fraction = new Fraction(2L, "Орда");
        HeroToFractionDTO heroToFractionDTOd = new HeroToFractionDTO(1L,
                hero
                , fraction);
        service.update(heroToFractionDTOd);
        assertEquals(heroToFractionDTOd.getHeroToFractionId(), 1L);
    }
    @Test
    void constructor() {
        HeroToFractionService fractionService = new HeroToFractionServiceImpl();
        assertNotNull(fractionService);
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}