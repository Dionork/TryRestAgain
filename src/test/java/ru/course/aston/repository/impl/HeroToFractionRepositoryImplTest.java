package ru.course.aston.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.course.aston.InitSchemeSql;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.model.Hero;
import ru.course.aston.model.HeroToFraction;
import ru.course.aston.repository.HeroToFractionRepository;
import ru.course.aston.repository.MaxHPRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HeroToFractionRepositoryImplTest {
    HeroToFractionRepository heroToFractionRepository = new HeroToFractionRepositoryImpl();
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
    void findById() {
        Optional<HeroToFraction> result = Optional.ofNullable(heroToFractionRepository.findById(1L));
        Hero hero = new Hero(4L, "Тралл", "HeroLastName", 1L);
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
        heroToFractionRepository.save(heroToFraction);
        Optional<HeroToFraction> result = Optional.ofNullable(heroToFractionRepository.findById(fraction.getFractionId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(heroToFraction.getFraction().getFractionName(),
                result.get().getFraction().getFractionName());
        Assertions.assertEquals(heroToFraction.getHero().getHeroName(),
                result.get().getHero().getHeroName());
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
}