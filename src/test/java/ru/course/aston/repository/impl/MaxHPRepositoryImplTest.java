package ru.course.aston.repository.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.model.MaxHP;
import ru.course.aston.repository.MaxHPRepository;

import java.util.Optional;

@Testcontainers
class MaxHPRepositoryImplTest {
    static MaxHPRepository maxHPRepository;
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
        maxHPRepository = new MaxHPRepositoryImpl(connectionManager);
    }

    @BeforeEach
    public void initSchema() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "sql/schema.sql");

    }


    @Test
    void findById() {
        MaxHP maxHP = new MaxHP(8L,
                new Hero(1L, "Hero", "HeroLastName", 1L),
                11110L
        );
        maxHPRepository.findById(1L);
        Optional<MaxHP> result = Optional.ofNullable(maxHPRepository.findById(1L));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(maxHP.getMaxHP(), result.get().getMaxHP()
        );
    }

    @Test
    void deleteById() {
        maxHPRepository.deleteById(8L);
        Optional<MaxHP> result = Optional.ofNullable(maxHPRepository.findById(8L));
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void save() {
        MaxHP maxHP = new MaxHP(8L,
                new Hero(1L, "Hero", "HeroLastName", 1L),
                123456L
        );
        Long id = maxHPRepository.save(maxHP).getMaxHPId();
        Optional<MaxHP> result = Optional.ofNullable(maxHPRepository.findById(id));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(maxHP.getMaxHP(), result.get().getMaxHP()
        );
        maxHPRepository.deleteById(id);
    }

    @Test
    void findAll() {
        Assertions.assertEquals(7, maxHPRepository.findAll().size());
    }

    @Test
    void update() {
        MaxHP maxHP = new MaxHP(2L,
                new Hero(1L, "NewHero", "NewHeroLastName", 1L),
                1234567L
        );
        maxHPRepository.update(maxHP);
        Optional<MaxHP> result = Optional.ofNullable(maxHPRepository.findById(2L));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(maxHP.getMaxHP(), result.get().getMaxHP()
        );

    }
    @Test
    void constructor() {
        MaxHPRepository maxHPRepository = new MaxHPRepositoryImpl();
        Assertions.assertNotNull(maxHPRepository);
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}