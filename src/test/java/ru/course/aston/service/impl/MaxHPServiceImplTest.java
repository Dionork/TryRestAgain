package ru.course.aston.service.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.service.MaxHPService;
import ru.course.aston.servlet.dto.MaxHPDTO;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class MaxHPServiceImplTest {
    static MaxHPService maxHPService;
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
        maxHPService = new MaxHPServiceImpl(connectionManager);
    }

    @BeforeEach
    public void initSchema() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "sql/schema.sql");

    }
    @Test
    void findById() {
        maxHPService.findById(1L);
        assertEquals(1L, maxHPService.findById(1L).getMaxHPId());
    }

    @Test
    void deleteById() {
        Hero hero = new Hero(2L,
                "Сильванна",
                "Ветрокрылова",
                3L);
        MaxHPDTO maxHPDTO = new MaxHPDTO(2L,hero,2222000L);
        Long id = maxHPService.save(maxHPDTO).getMaxHPId();
        maxHPService.deleteById(id);
        assertNull(maxHPService.findById(id));
    }

    @Test
    void save() {
        Hero hero = new Hero(2L,
                "Сильванна",
                "Ветрокрылова",
                3L);
        MaxHPDTO maxHPDTO = new MaxHPDTO(2L,hero,2222000L);
        Long id = maxHPService.save(maxHPDTO).getMaxHPId();
        assertEquals(2222000L, maxHPService.findById(id).getMaxHP());
        maxHPService.deleteById(id);
    }

    @Test
    void findAll() {
        maxHPService.findAll();
        assertEquals(1L, maxHPService.findAll().get(0).getMaxHPId());
    }

    @Test
    void update() {
        Hero hero = new Hero(2L,
                "Сильванна",
                "Ветрокрылова",
                3L);
        MaxHPDTO maxHPDTO = new MaxHPDTO(2L,hero,22220L);
        maxHPService.update(maxHPDTO);
        assertEquals(22220L, maxHPService.findById(2L).getMaxHP());
    }
@Test
void constructor() {
        MaxHPService service = new MaxHPServiceImpl();
        assertNotNull(service);
}
    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}