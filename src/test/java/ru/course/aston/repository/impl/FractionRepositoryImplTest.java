package ru.course.aston.repository.impl;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.repository.FractionRepository;

import java.sql.SQLException;
import java.util.Optional;

@Testcontainers
class FractionRepositoryImplTest {
    FractionRepository fractionRepository = new FractionRepositoryImpl();
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
    public void createFraction() {
        Fraction fraction = new Fraction(3L, "Fraction");
        Long id = fractionRepository.save(fraction).getFractionId();
        Optional<Fraction> result = Optional.ofNullable(fractionRepository.findById(id));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(fraction.getFractionName(), result.get().getFractionName());
        fractionRepository.deleteById(id);
    }

    @Test
    public void updateFraction() {
        Fraction fraction = new Fraction(2L, "Бурда");
        fractionRepository.update(fraction);
        Optional<Fraction> result = Optional.ofNullable(fractionRepository.findById(fraction.getFractionId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(fraction.getFractionName(), result.get().getFractionName());
        fractionRepository.update(new Fraction(2L, "Орда"));

    }

    @Test
    public void findByIdFraction() {
        Long id = fractionRepository.findById(2L).getFractionId();
        Assertions.assertEquals(id, fractionRepository.findById(2L).getFractionId());
    }

    @Test
    public void deleteFraction() {
        fractionRepository.deleteById(11L);
        Optional<Fraction> result = Optional.ofNullable(fractionRepository.findById(3L));
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void findAllFraction() {
        Assertions.assertEquals(2, fractionRepository.findAll().size());
    }


    @AfterAll
    public static void stopContainer() {
        System.out.println("Стоп контейнера");
        container.stop();
    }
}