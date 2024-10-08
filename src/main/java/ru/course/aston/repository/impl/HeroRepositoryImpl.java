package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.repository.HeroRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {
    private final ConnectionManager connectionManager;
    public HeroRepositoryImpl(){
    connectionManager = new ConnectionManagerImpl();
    }
    public HeroRepositoryImpl(ConnectionManager connectionManager){
    this.connectionManager = connectionManager;
    }

    @Override
    public Hero findById(Long id) {
        String sql = "SELECT * FROM wow_db.heroes WHERE hero_id =" + id;
        if (id == null){
            throw new NullPointerException();
        }
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Hero(
                        resultSet.getLong("hero_id"),
                        resultSet.getString("hero_name"),
                        resultSet.getString("hero_lastname"),
                        resultSet.getLong("role_name_id")

                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null){
            throw new NullPointerException();
        }
        String sql = "DELETE FROM wow_db.heroes WHERE hero_id =" + id;
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hero save(Hero hero) {
        if (hero == null){
            throw new NullPointerException();
        }
        String sql = "INSERT INTO wow_db.heroes (hero_name, hero_lastname, role_name_id) VALUES (?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
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
        String sql = "SELECT * FROM wow_db.heroes";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
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
        if (models == null){
            throw new NullPointerException();
        }
        String sql = "UPDATE wow_db.heroes SET hero_name = ?, hero_lastname = ?, role_name_id = ? WHERE hero_id = ?";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){
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
