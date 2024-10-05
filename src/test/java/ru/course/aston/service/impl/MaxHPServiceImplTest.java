package ru.course.aston.service.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.service.MaxHPService;
import ru.course.aston.servlet.dto.MaxHPDTO;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class MaxHPServiceImplTest {
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
        MaxHPService service = new MaxHPServiceImpl();
        service.findById(1L);
        assertEquals(1L, service.findById(1L).getMaxHPId());
    }

    @Test
    void deleteById() {
        Hero hero = new Hero(2L,
                "Сильванна",
                "Ветрокрылова",
                3L);
        MaxHPDTO maxHPDTO = new MaxHPDTO(2L,hero,2222000L);
        MaxHPService service = new MaxHPServiceImpl();
        Long id = service.save(maxHPDTO).getMaxHPId();
        service.deleteById(id);
        assertNull(service.findById(id));
    }

    @Test
    void save() {
        Hero hero = new Hero(2L,
                "Сильванна",
                "Ветрокрылова",
                3L);
        MaxHPDTO maxHPDTO = new MaxHPDTO(2L,hero,2222000L);
        MaxHPService service = new MaxHPServiceImpl();
        Long id = service.save(maxHPDTO).getMaxHPId();
        assertEquals(2222000L, service.findById(id).getMaxHP());
        service.deleteById(id);
    }

    @Test
    void findAll() {
        MaxHPService service = new MaxHPServiceImpl();
        service.findAll();
        assertEquals(1L, service.findAll().get(0).getMaxHPId());
    }

    @Test
    void update() {
        Hero hero = new Hero(2L,
                "Сильванна",
                "Ветрокрылова",
                3L);
        MaxHPDTO maxHPDTO = new MaxHPDTO(2L,hero,22220L);
        MaxHPService service = new MaxHPServiceImpl();
        service.update(maxHPDTO);
        assertEquals(22220L, service.findById(2L).getMaxHP());
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}