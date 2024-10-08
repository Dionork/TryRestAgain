package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.model.MaxHP;
import ru.course.aston.repository.HeroRepository;
import ru.course.aston.repository.MaxHPRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MaxHPRepositoryImpl implements MaxHPRepository {
    private final ConnectionManager connectionManager;
    private final HeroRepository heroRepository;
    public MaxHPRepositoryImpl() {
        connectionManager = new ConnectionManagerImpl();
        heroRepository = new HeroRepositoryImpl();
    }
    public MaxHPRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.heroRepository = new HeroRepositoryImpl(connectionManager);
    }

    @Override
    public MaxHP findById(Long id) {
        String sql = "select * from wow_db.heroes_maxhp where heroes_maxhp_id = " + id;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Hero hero = heroRepository.findById(resultSet.getLong("hero_id"));
                return new MaxHP(resultSet.getLong("heroes_maxhp_id"),
                        hero,
                        resultSet.getLong("maxhp"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "delete from wow_db.heroes_maxhp where heroes_maxhp_id = " + id;
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MaxHP save(MaxHP maxHP) {
        String sql = "insert into wow_db.heroes_maxhp (hero_id, maxhp) values (?, ?)";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, maxHP.getHero().getHeroId());
            statement.setLong(2, maxHP.getMaxHP());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Hero hero = heroRepository.findById(maxHP.getHero().getHeroId());
                maxHP = new MaxHP(generatedKeys.getLong(1),
                        hero,
                        maxHP.getMaxHP());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maxHP;
    }

    @Override
    public List<MaxHP> findAll() {
        List<MaxHP> maxHPList = new ArrayList<>();
        String sql = "SELECT* FROM wow_db.heroes_maxhp";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MaxHP maxHP = new MaxHP(resultSet.getLong("heroes_maxhp_id"),
                        heroRepository.findById(resultSet.getLong("hero_id")),
                        resultSet.getLong("maxhp"));
                maxHPList.add(maxHP);
            }
            return maxHPList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(MaxHP maxHP) {
        String sql = "update wow_db.heroes_maxhp set maxhp = ?, hero_id =? where heroes_maxhp_id = ?";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, maxHP.getMaxHP());
            statement.setLong(2, maxHP.getHero().getHeroId());
            statement.setLong(3, maxHP.getMaxHPId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
