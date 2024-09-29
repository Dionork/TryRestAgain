package ru.course.aston.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.course.aston.InitSchemeSql;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.repository.HeroRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

class HeroRepositoryImplTest {
    HeroRepository heroRepository = new HeroRepositoryImpl();
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
        heroRepository.save(hero);
        Optional<Hero> result = Optional.ofNullable(heroRepository.findById(hero.getHeroId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(hero.getHeroName(),result.get().getHeroName());
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
}