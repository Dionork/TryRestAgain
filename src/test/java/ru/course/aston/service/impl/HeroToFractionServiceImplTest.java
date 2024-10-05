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
import ru.course.aston.model.Fraction;
import ru.course.aston.model.Hero;
import ru.course.aston.model.HeroToFraction;
import ru.course.aston.service.HeroToFractionService;
import ru.course.aston.servlet.dto.HeroToFractionDTO;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class HeroToFractionServiceImplTest {
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
        HeroToFractionService service = new HeroToFractionServiceImpl();
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
        HeroToFractionService service = new HeroToFractionServiceImpl();
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
        HeroToFractionService service = new HeroToFractionServiceImpl();
       Long id = service.save(heroToFractionDTOd).getHeroToFractionId();
        assertEquals(heroToFractionDTOd.getHeroToFractionId(), 1L);
        service.deleteById(id);
    }

    @Test
    void findAll() {
        HeroToFractionServiceImpl service = new HeroToFractionServiceImpl();
        assertEquals(7, service.findAll().size());
    }

    @Test
    void update() {
        Hero hero = new Hero(4L, "Артас", "HeroLastName", 1L);
        Fraction fraction = new Fraction(2L, "Орда");
        HeroToFractionDTO heroToFractionDTOd = new HeroToFractionDTO(1L,
                hero
                , fraction);
        HeroToFractionService service = new HeroToFractionServiceImpl();
        service.update(heroToFractionDTOd);
        assertEquals(heroToFractionDTOd.getHeroToFractionId(), 1L);
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}