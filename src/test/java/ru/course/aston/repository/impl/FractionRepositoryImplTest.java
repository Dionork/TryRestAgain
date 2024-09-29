package ru.course.aston.repository.impl;

import org.junit.jupiter.api.*;
import ru.course.aston.InitSchemeSql;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.repository.FractionRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
class FractionRepositoryImplTest {
    FractionRepository fractionRepository = new FractionRepositoryImpl();
    @BeforeAll
    public static void initSQL() {
        ConnectionManager connectionManager = new ConnectionManagerImpl();
        PreparedStatement statement;
        try {
            statement = connectionManager.getConnection().prepareStatement(InitSchemeSql.INIT_SCHEME_SQL);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
    }
@Test
    public void createFraction() {
    Fraction fraction = new Fraction(3L,"Fraction");
    fractionRepository.save(fraction);
    Optional<Fraction> result = Optional.ofNullable(fractionRepository.findById(fraction.getFractionId()));
    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(fraction.getFractionName(),result.get().getFractionName());
}
@Test
    public void updateFraction() {
    Fraction fraction = new Fraction(2L,"Бурда");
    fractionRepository.update(fraction);
    Optional<Fraction> result = Optional.ofNullable(fractionRepository.findById(fraction.getFractionId()));
   Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(fraction.getFractionName(),result.get().getFractionName());
}
@Test
public void findByIdFraction() {
    Fraction fraction = new Fraction(2L,"Орда");
    Optional<Fraction> result = Optional.ofNullable(fractionRepository.findById(fraction.getFractionId()));
   Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(fraction.getFractionName(),result.get().getFractionName());
}
@Test
    public void deleteFraction() {
    fractionRepository.deleteById(3L);
    Optional<Fraction> result = Optional.ofNullable(fractionRepository.findById(3L));
    Assertions.assertFalse(result.isPresent());
}
    @Test
    public void findAllFraction() {
        Assertions.assertEquals(3,fractionRepository.findAll().size());
    }
}