package ru.course.aston.service.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.service.RoleService;
import ru.course.aston.servlet.dto.RoleDTO;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class RoleServiceImplTest {
    static RoleService roleService;
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
        roleService = new RoleServiceImpl(connectionManager);
    }

    @BeforeEach
    public void initSchema() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "sql/schema.sql");
    }

    @Test
    void findById() {
        roleService.findById(1L);
        assertEquals("Воин", roleService.findById(1L).getRoleName());
    }

    @Test
    void deleteById() {
        RoleDTO roleDTO = new RoleDTO(1L, "newRole");
        Long id = roleService.save(roleDTO).getRoleNameId();
        roleService.deleteById(id);
        assertNull(roleService.findById(id));
    }

    @Test
    void save() {
        RoleDTO roleDTO = new RoleDTO(1L, "newRole");
        Long id = roleService.save(roleDTO).getRoleNameId();
        assertEquals("newRole", roleService.findById(id).getRoleName());
        roleService.deleteById(id);
    }

    @Test
    void findAll() {
        roleService.findAll();
        assertEquals(3, roleService.findAll().size());
    }

    @Test
    void update() {
        RoleDTO roleDTO = new RoleDTO(1L, "Воин");
        roleService.update(roleDTO);
        assertEquals(roleService.findById(1L).getRoleName(), roleDTO.getRoleName());
    }

    @Test
    void constructor() {
        RoleService service = new RoleServiceImpl();
        assertNotNull(service);
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}