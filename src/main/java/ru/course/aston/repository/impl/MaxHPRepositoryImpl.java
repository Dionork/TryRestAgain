package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.model.MaxHP;
import ru.course.aston.repository.HeroRepository;
import ru.course.aston.repository.MaxHPRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MaxHPRepositoryImpl implements MaxHPRepository {
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private PreparedStatement statement;
    private HeroRepository heroRepository = new HeroRepositoryImpl();

    @Override
    public MaxHP findById(Long id) {
        try {
            String sql = "select * from wow_db.heroes_maxhp where heroes_maxhp_id = " + id;
            statement = connectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Hero hero = heroRepository.findById(resultSet.getLong("hero_id"));
                return new MaxHP(resultSet.getLong("heroes_maxhp_id"),
                        hero,
                        resultSet.getLong("maxhp"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = "delete from wow_db.heroes_maxhp where heroes_maxhp_id = " + id;
            statement = connectionManager.getConnection().prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public MaxHP save(MaxHP maxHP) {
        try {
            String sql = "insert into wow_db.heroes_maxhp (hero_id, maxhp) values (?, ?)";
            statement = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           statement.setLong(1, maxHP.getHero().getHeroId());
           statement.setLong(2, maxHP.getMaxHP());
           statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Hero hero = heroRepository.findById( maxHP.getHero().getHeroId());
                maxHP = new MaxHP(generatedKeys.getLong(1),
                        hero,
                        maxHP.getMaxHP());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
        return maxHP;
    }

    @Override
    public List<MaxHP> findAll() {
        List<MaxHP> maxHPList = new ArrayList<>();
        try {
            String sql = "SELECT* FROM wow_db.heroes_maxhp";
            statement = connectionManager.getConnection().prepareStatement(sql);
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
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public void update(MaxHP maxHP) {
        try {
            String sql = "update wow_db.heroes_maxhp set maxhp = ?, hero_id =? where heroes_maxhp_id = ?";
            statement = connectionManager.getConnection().prepareStatement(sql);
            statement.setLong(1, maxHP.getMaxHP());
            statement.setLong(2, maxHP.getHero().getHeroId());
            statement.setLong(3, maxHP.getMaxHPId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();

        }
    }
}
