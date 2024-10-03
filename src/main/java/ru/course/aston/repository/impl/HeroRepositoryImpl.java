package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.repository.HeroRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private PreparedStatement statement;


    @Override
    public Hero findById(Long id) {
        try {
            String sql = "SELECT * FROM wow_db.heroes WHERE hero_id =" + id;
            statement = connectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Hero hero = new Hero(
                        resultSet.getLong("hero_id"),
                        resultSet.getString("hero_name"),
                        resultSet.getString("hero_lastname"),
                        resultSet.getLong("role_name_id")

                );
                return hero;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = "DELETE FROM wow_db.heroes WHERE hero_id =" + id;
            statement = connectionManager.getConnection().prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hero save(Hero hero) {
        try {
            String sql = "INSERT INTO wow_db.heroes (hero_name, hero_lastname, role_name_id) VALUES (?, ?, ?)";
            statement = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, hero.getHeroName());
            statement.setString(2, hero.getHeroLastName());
            statement.setLong(3, hero.getRoleNameId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                hero = new Hero(
                        generatedKeys.getLong(1),
                        hero.getHeroName(),
                        hero.getHeroLastName(),
                        hero.getRoleNameId());
            }
            return hero;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Hero> findAll() {
        List<Hero> heroList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM wow_db.heroes";
            statement = connectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Hero hero = new Hero(
                        resultSet.getLong("hero_id"),
                        resultSet.getString("hero_name"),
                        resultSet.getString("hero_lastname"),
                        resultSet.getLong("role_name_id")
                );
                heroList.add(hero);
            }
            return heroList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Hero models) {
        try {
            String sql = "UPDATE wow_db.heroes SET hero_name = ?, hero_lastname = ?, role_name_id = ? WHERE hero_id = ?";
            statement = connectionManager.getConnection().prepareStatement(sql);
            statement.setString(1, models.getHeroName());
            statement.setString(2, models.getHeroLastName());
            statement.setLong(3, models.getRoleNameId());
            statement.setLong(4, models.getHeroId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
