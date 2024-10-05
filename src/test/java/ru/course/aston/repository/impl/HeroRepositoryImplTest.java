package ru.course.aston.repository.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.repository.HeroRepository;

import java.sql.SQLException;
import java.util.Optional;
@Testcontainers
class HeroRepositoryImplTest {
    HeroRepository heroRepository = new HeroRepositoryImpl();
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
        Hero hero = new Hero(2L,"Сильвана", "Ветрокрылова", 3L);
        heroRepository.findById(1L);
        Optional<Hero> result = Optional.ofNullable(heroRepository.findById(hero.getHeroId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(hero.getHeroName(),result.get().getHeroName());
    }

    @Test
    void deleteById() {
        heroRepository.deleteById(8L);
        Optional<Hero> result = Optional.ofNullable(heroRepository.findById(8L));
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void save() {
        Hero hero = new Hero(8L, "Hero", "HeroLastName", 1L);
        Long id = heroRepository.save(hero).getHeroId();
        Optional<Hero> result = Optional.ofNullable(heroRepository.findById(id));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(hero.getHeroName(),result.get().getHeroName());
        heroRepository.deleteById(id);
    }

    @Test
    void findAll() {
        Assertions.assertEquals(7,heroRepository.findAll().size());
    }

    @Test
    void update() {
        Hero hero = new Hero(1L, "NewHero", "NewHeroLastName", 2L);
        heroRepository.update(hero);
        Optional<Hero> result = Optional.ofNullable(heroRepository.findById(hero.getHeroId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(hero.getHeroName(),result.get().getHeroName());
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}