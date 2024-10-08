package ru.course.aston.service.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.service.HeroService;
import ru.course.aston.servlet.dto.HeroDTO;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class HeroServiceImplTest {
    static HeroService heroService;
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
        heroService = new HeroServiceImpl(connectionManager);
    }

    @BeforeEach
    public void initSchema() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "sql/schema.sql");

    }
    @Test
    void findById() {
        HeroDTO heroDTO = heroService.findById(1L);
        assertEquals(1L,heroDTO.getHeroId() );
    }

    @Test
    void deleteById() {
        HeroDTO heroDTO = new HeroDTO(
                1L,
                "Hero",
                "Last",
                2L
        );
        Long id = heroService.save(heroDTO).getHeroId();
        heroService.deleteById(id);
        assertNull(heroService.findById(id));
    }

    @Test
    void save() {
        HeroDTO heroDTO = new HeroDTO(
                1L,
                "Hero",
                "Last",
                2L
        );
        Long id = heroService.save(heroDTO).getHeroId();
        assertEquals(1L,heroService.findById(1L).getHeroId() );
        heroService.deleteById(id);
    }

    @Test
    void findAll() {
        assertEquals(7,heroService.findAll().size() );
    }

    @Test
    void update() {
        HeroDTO heroDTO = new HeroDTO(
                1L,
                "Тралл",
                "Огримаров",
                1L
        );
        heroService.update(heroDTO);
        assertEquals("Тралл",heroService.findById(1L).getHeroName() );

    }
    @Test
    void constructor() {
        HeroService service = new HeroServiceImpl();
        assertNotNull(service);
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}