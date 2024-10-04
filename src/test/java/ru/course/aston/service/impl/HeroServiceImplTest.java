package ru.course.aston.service.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.service.HeroService;
import ru.course.aston.servlet.dto.HeroDTO;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class HeroServiceImplTest {
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
        HeroService heroService = new HeroServiceImpl();
        HeroDTO heroDTO = heroService.findById(1L);
        assertEquals(heroDTO.getHeroId(), 1L);
    }

    @Test
    void deleteById() {
        HeroDTO heroDTO = new HeroDTO(
                1L,
                "Hero",
                "Last",
                2L
        );
        HeroService heroService = new HeroServiceImpl();
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
        HeroService heroService = new HeroServiceImpl();
        Long id = heroService.save(heroDTO).getHeroId();
        assertEquals(heroService.findById(1L).getHeroId(), 1L);
        heroService.deleteById(id);
    }

    @Test
    void findAll() {
        HeroService heroService = new HeroServiceImpl();
        assertEquals(heroService.findAll().size(), 7);
    }

    @Test
    void update() {
        HeroDTO heroDTO = new HeroDTO(
                1L,
                "Тралл",
                "Огримаров",
                1L
        );
        HeroService heroService = new HeroServiceImpl();
        heroService.update(heroDTO);
        assertEquals(heroService.findById(1L).getHeroName(), "Тралл");

    }
    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}