package ru.course.aston.service.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.service.RoleService;
import ru.course.aston.servlet.dto.RoleDTO;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class RoleServiceImplTest {
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
        RoleService roleService = new RoleServiceImpl();
        roleService.findById(1L);
        assertEquals(roleService.findById(1L).getRoleName(),"Воин");
    }

    @Test
    void deleteById() {
        RoleDTO roleDTO = new RoleDTO(1L,"newRole");
        RoleServiceImpl roleService = new RoleServiceImpl();
        Long id = roleService.save(roleDTO).getRoleNameId();
        roleService.deleteById(id);
        assertEquals(roleService.findById(id), null);
    }

    @Test
    void save() {
        RoleDTO roleDTO = new RoleDTO(1L,"newRole");
        RoleServiceImpl roleService = new RoleServiceImpl();
        Long id = roleService.save(roleDTO).getRoleNameId();
        assertEquals(roleService.findById(id).getRoleName(),"newRole");
        roleService.deleteById(id);
    }

    @Test
    void findAll() {
        RoleService roleService = new RoleServiceImpl();
        roleService.findAll();
        assertEquals(roleService.findAll().size(), 3);
    }

    @Test
    void update() {
        RoleDTO roleDTO = new RoleDTO(1L,"Воин");
        RoleServiceImpl roleService = new RoleServiceImpl();
        roleService.update(roleDTO);
        assertEquals(roleService.findById(1L).getRoleName(),roleDTO.getRoleName());
    }
    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}