package ru.course.aston.repository.impl;


import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.repository.FractionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void findByIdFractionNull() {
        assertThrows(NullPointerException.class, () -> fractionRepository.findById(null));
    }

    @Test
    void saveFractionNull() {
        assertThrows(NullPointerException.class, () -> fractionRepository.save(null));
    }


    @Test
    void updateFractionNull() {
        assertThrows(NullPointerException.class, () -> fractionRepository.update(null));
    }

    @Test
    void deleteByIdFractionNull() {
        assertThrows(NullPointerException.class, () -> fractionRepository.deleteById(null));
    }

    @Test
    void findByIdFractionSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement
                        = connection.prepareStatement("SELECT * FROM fraction WHERE fraction_id = ?");
                statement.setLong(1, 1);
                statement.executeQuery();
            }
        });
    }

    @Test
    void saveFractionSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement
                        = connection.prepareStatement("INSERT INTO fraction (fraction_id, fraction_name) VALUES (?, ?)");
                statement.setLong(1, 1);
                statement.setString(2, "Fraction");
                statement.executeUpdate();
            }
        });
    }

    @Test
    void updateFractionSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement
                        = connection.prepareStatement("UPDATE fraction SET fraction_name = ? WHERE fraction_id = ?");
                statement.setString(1, "Fraction");
                statement.setLong(2, 1);
                statement.executeUpdate();
            }
        });
    }

    @Test
    void deleteByIdFractionSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement
                        = connection.prepareStatement("DELETE FROM fraction WHERE fraction_id = ?");
                statement.setLong(1, 1);
                statement.executeUpdate();
            }
        });
    }

    @Test
    void findAllFractionSQLException() {
        assertThrows(SQLException.class, () -> {
            try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
                PreparedStatement statement
                        = connection.prepareStatement("SELECT * FROM fraction");
                statement.executeQuery();
            }
        });
    }
}