package ru.course.aston.repository.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.model.Hero;
import ru.course.aston.model.HeroToFraction;
import ru.course.aston.repository.HeroToFractionRepository;

import java.sql.SQLException;
import java.util.Optional;
@Testcontainers
class HeroToFractionRepositoryImplTest {
    HeroToFractionRepository heroToFractionRepository = new HeroToFractionRepositoryImpl();
    @Container
    public static final GenericContainer<?> container = new PostgreSQLContainer<>("postgres:14-alpine")
//         .withCommand("docker-compose up docker run -p 8080:8080 tryrestagain");
            .withInitScript("sql/schema.sql");

    @BeforeAll
    public static void startContainer() {
        System.out.println("Старт контейнера");
        container.start();

    }

    @BeforeEach
    void setUp() {
        ConnectionManager connection = new ConnectionManagerImpl();
        try {
            System.out.println("Стартация контейнера");
            connection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findById() {
        Optional<HeroToFraction> result = Optional.ofNullable(heroToFractionRepository.findById(1L));
        Hero hero = new Hero(4L, "NewHero", "HeroLastName", 1L);
        Fraction fraction = new Fraction(2L,"Орда");
        HeroToFraction heroToFraction = new HeroToFraction(2L,hero,fraction);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(heroToFraction.getFraction().getFractionName(),
                result.get().getFraction().getFractionName());
        Assertions.assertEquals(heroToFraction.getHero().getHeroName(),
                result.get().getHero().getHeroName());
    }

    @Test
    void deleteById() {
        heroToFractionRepository.deleteById(8L);
        Optional<HeroToFraction> result = Optional.ofNullable(heroToFractionRepository.findById(8L));
        Assertions.assertFalse(result.isPresent());

    }

    @Test
    void save() {
        Hero hero = new Hero(4L, "Артас", "HeroLastName", 1L);
        Fraction fraction = new Fraction(2L,"Орда");
        HeroToFraction heroToFraction = new HeroToFraction(2L,hero,fraction);
        Long id = heroToFractionRepository.save(heroToFraction).getHeroToFractionId();
        Optional<HeroToFraction> result = Optional.ofNullable(heroToFractionRepository.findById(fraction.getFractionId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(heroToFraction.getFraction().getFractionName(),
                result.get().getFraction().getFractionName());
        Assertions.assertEquals(heroToFraction.getHero().getHeroName(),
                result.get().getHero().getHeroName());
        heroToFractionRepository.deleteById(id);
    }

    @Test
    void findAll() {
        Assertions.assertEquals(7, heroToFractionRepository.findAll().size());
    }

    @Test
    void update() {
        Hero hero = new Hero(4L, "Артас", "HeroLastName", 1L);
        Fraction fraction = new Fraction(2L,"Орда");
        HeroToFraction heroToFraction = new HeroToFraction(2L,hero,fraction);
        heroToFractionRepository.update(heroToFraction);
        Optional<HeroToFraction> result = Optional.ofNullable(heroToFractionRepository.findById(fraction.getFractionId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(heroToFraction.getFraction().getFractionName(),result.get().getFraction().getFractionName());
    }
    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}