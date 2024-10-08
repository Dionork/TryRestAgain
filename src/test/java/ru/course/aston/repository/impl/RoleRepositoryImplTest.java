package ru.course.aston.repository.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Role;
import ru.course.aston.repository.RoleRepository;

import java.util.Optional;

@Testcontainers
class RoleRepositoryImplTest {
    static RoleRepository roleRepository;
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
        roleRepository = new RoleRepositoryImpl(connectionManager);
    }

    @BeforeEach
    public void initSchema() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "sql/schema.sql");

    }

    @Test
    void findById() {
        Long id = roleRepository.findById(1L).getRoleNameId();
        Assertions.assertEquals(id, roleRepository.findById(1L).getRoleNameId());
    }

    @Test
    void deleteById() {
        roleRepository.deleteById(4L);
        Optional<Role> result = Optional.ofNullable(roleRepository.findById(4L));
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void save() {
        Role role = new Role(1L, "RoleName");
        Long id = roleRepository.save(role).getRoleNameId();
        Optional<Role> result = Optional.ofNullable(roleRepository.findById(id));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(role.getRoleName(), result.get().getRoleName());
        roleRepository.deleteById(id);
    }

    @Test
    void findAll() {
        Assertions.assertEquals(3, roleRepository.findAll().size());
    }

    @Test
    void update() {
        Role role = new Role(1L, "newRoleName");
        roleRepository.update(role);
        Optional<Role> result = Optional.ofNullable(roleRepository.findById(role.getRoleNameId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(role.getRoleName(), result.get().getRoleName());
    }
    @Test
    void constructor() {
        RoleRepository roleRepository = new RoleRepositoryImpl();
        Assertions.assertNotNull(roleRepository);
    }
    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}