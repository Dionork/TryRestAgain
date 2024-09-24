package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.HeroToFraction;
import ru.course.aston.repository.HeroToFractionRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class HeroToFractionRepositoryImpl implements HeroToFractionRepository {
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
    public HeroToFraction findById(Long id) {
        try {
            statement.executeQuery("SELECT * FROM hero_to_fraction WHERE id = " + id);
            while (statement.getResultSet().next()) {
                return new HeroToFraction(
                        statement.getResultSet().getLong("heroes_fraction_id"),
                        statement.getResultSet().getLong("fraction_id"),
                        statement.getResultSet().getLong("heroes_id")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            statement.executeUpdate("DELETE FROM hero_to_fraction WHERE id = " + id);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public HeroToFraction save(HeroToFraction entity) {
        try {
            statement.executeUpdate("INSERT INTO hero_to_fraction (heroes_fraction_id, fraction_id, heroes_id) " +
                                    "VALUES ("
                                    + entity.getHeroToFractionId() + ", "
                                    + entity.getFractionId() + ", "
                                    + entity.getHeroId() + ")");
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public List<HeroToFraction> findAll() {
        List<HeroToFraction> HeroToFractionList = null;
        try {
            statement.executeQuery("SELECT * FROM hero_to_fraction");
            while (statement.getResultSet().next()) {
                HeroToFractionList.add(new HeroToFraction(
                        statement.getResultSet().getLong("heroes_fraction_id"),
                        statement.getResultSet().getLong("fraction_id"),
                        statement.getResultSet().getLong("heroes_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
        return HeroToFractionList;

    }
}
