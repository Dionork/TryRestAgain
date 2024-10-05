package ru.course.aston.repository.impl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startable;
import org.testcontainers.lifecycle.Startables;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Role;
import ru.course.aston.repository.RoleRepository;

import java.sql.SQLException;
import java.util.Optional;

@Testcontainers
class RoleRepositoryImplTest {
    @Container
    public static final GenericContainer<?> container = new PostgreSQLContainer<>("postgres:14-alpine")
//         .withCommand("docker-compose up docker run -p 8080:8080 tryrestagain");
            .withInitScript("sql/schema.sql");

    @BeforeAll
    public static void startContainer() {
        System.out.println("Старт контейнера");
        container.start();

    }




    RoleRepository roleRepository = new RoleRepositoryImpl();

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
    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}