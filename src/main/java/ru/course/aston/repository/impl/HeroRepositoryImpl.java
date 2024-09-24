package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.repository.HeroRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private Statement statement;

    {
        try {
            statement = connectionManager.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Hero findById(Long id) {
        try {
            statement.execute("SELECT * FROM heroes WHERE hero_id = " + id);
            while (statement.getResultSet().next()) {
                return new Hero(
                        statement.getResultSet().getLong("hero_id"),
                        statement.getResultSet().getString("hero_name"),
                        statement.getResultSet().getString("hero_lastname"),
                        statement.getResultSet().getLong("role_name_id")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            statement.execute("DELETE FROM heroes WHERE hero_id = " + id);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public Hero save(Hero hero) {
        try {
            statement.execute("INSERT INTO heroes (hero_name, hero_lastname, role_name_id) VALUES ('"
                              + hero.getHeroName() + "', '"
                              + hero.getHeroLastName() + "', "
                              + hero.getRoleNameId() + ")");
            return hero;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public List<Hero> findAll() {
        List<Hero> heroList = null;
        try {
            statement.execute("SELECT * FROM heroes");
            while (statement.getResultSet().next()) {
                heroList.add(
                        new Hero(
                                statement.getResultSet().getLong("hero_id"),
                                statement.getResultSet().getString("hero_name"),
                                statement.getResultSet().getString("hero_lastname"),
                                statement.getResultSet().getLong("role_name_id")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
        return heroList;
    }
}
