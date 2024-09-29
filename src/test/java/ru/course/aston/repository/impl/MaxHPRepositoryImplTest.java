package ru.course.aston.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.course.aston.InitSchemeSql;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.model.MaxHP;
import ru.course.aston.repository.MaxHPRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

class MaxHPRepositoryImplTest {
    MaxHPRepository maxHPRepository = new MaxHPRepositoryImpl();
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
        MaxHP maxHP = new MaxHP(8L,
                new Hero(1L, "Hero", "HeroLastName", 1L),
                11110L
        );
        maxHPRepository.findById(1L);
        Optional<MaxHP> result = Optional.ofNullable(maxHPRepository.findById(1L));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(maxHP.getMaxHP(),result.get().getMaxHP()
        );
    }

    @Test
    void deleteById() {
        maxHPRepository.deleteById(8L);
        Optional<MaxHP> result = Optional.ofNullable(maxHPRepository.findById(8L));
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void save() {
        MaxHP maxHP = new MaxHP(8L,
                new Hero(1L, "Hero", "HeroLastName", 1L),
                123456L
        );
        maxHPRepository.save(maxHP);
        Optional<MaxHP> result = Optional.ofNullable(maxHPRepository.findById(8L));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(maxHP.getMaxHP(),result.get().getMaxHP()
        );
    }

    @Test
    void findAll() {
        Assertions.assertEquals(7, maxHPRepository.findAll().size());
    }

    @Test
    void update() {
        MaxHP maxHP = new MaxHP(2L,
                new Hero(1L, "NewHero", "NewHeroLastName", 1L),
                1234567L
        );
        maxHPRepository.update(maxHP);
        Optional<MaxHP> result = Optional.ofNullable(maxHPRepository.findById(2L));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(maxHP.getMaxHP(),result.get().getMaxHP()
        );

    }
}