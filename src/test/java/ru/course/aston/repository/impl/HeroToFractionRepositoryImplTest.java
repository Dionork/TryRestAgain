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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void findById() {
        Assertions.assertEquals(2L, heroToFractionRepository
                .findById(2L).getHeroToFractionId());
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
        Fraction fraction = new Fraction(2L, "Орда");
        HeroToFraction heroToFraction = new HeroToFraction(2L, hero, fraction);
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
        Fraction fraction = new Fraction(2L, "Орда");
        HeroToFraction heroToFraction = new HeroToFraction(2L, hero, fraction);
        heroToFractionRepository.update(heroToFraction);
        Optional<HeroToFraction> result = Optional.ofNullable(heroToFractionRepository.findById(fraction.getFractionId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(heroToFraction.getFraction().getFractionName(), result.get().getFraction().getFractionName());
    }

    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }

    @Test
    void findByIdSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM hero_to_fraction WHERE hero_to_fraction_id = ?");
                statement.setLong(1, 1L);
                statement.executeQuery();
            }
        });
    }

    @Test
    void saveNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            heroToFractionRepository.save(null);
        });
    }

    @Test
    void updateHeroToFractionSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement("UPDATE hero_to_fraction SET fraction_id = ? WHERE hero_to_fraction_id = ?");
                statement.setLong(1, 1L);
                statement.setLong(2, 2L);
                statement.executeQuery();
            }
        });
    }

    @Test
    void findAllSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM hero_to_fraction");
                statement.executeQuery();
            }
        });
    }

    @Test
    void findByIdNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            heroToFractionRepository.findById(null);
        });
    }

    @Test
    void deleteByIdNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            heroToFractionRepository.deleteById(null);
        });
    }

    @Test
    void updateNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            heroToFractionRepository.update(null);
        });
    }


    @Test
    void findByIdHeroToFractionNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            heroToFractionRepository.findById(null);
        });
    }

    @Test
    void findByIdHeroToFractionSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement
                        = connection.prepareStatement("SELECT * FROM hero_to_fraction WHERE hero_to_fraction_id = ?");
                statement.setLong(1, 1L);
                statement.executeQuery();

            }
        });
    }

    @Test
    void deleteByIdHeroToFractionSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement
                        = connection.prepareStatement("DELETE FROM hero_to_fraction WHERE hero_to_fraction_id = ?");
                statement.setLong(1, 1L);
                statement.executeQuery();

            }
        });
    }

    @Test
    void deleteByIdHeroToFractionNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            heroToFractionRepository.deleteById(null);
        });
    }

    @Test
    void saveHeroToFractionSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO hero_to_fraction (hero_to_fraction_id, hero_id, fraction_id) VALUES (?, ?, ?)");
                statement.setLong(1, 1L);
                statement.setLong(2, 1L);
                statement.setLong(3, 1L);
                statement.executeQuery();

            }
        });
    }
    @Test
    void saveHeroToFractionNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            heroToFractionRepository.save(null);
        });
    }

    @Test
    void updateHeroToFractionNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            heroToFractionRepository.update(null);
        });
    }

}