package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.MaxHP;
import ru.course.aston.repository.MaxHPRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MaxHPRepositoryImpl implements MaxHPRepository {
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
    public MaxHP findById(Long id) {
        try {
            statement.executeQuery("select * from wow_db.heroes_maxhp where heroes_maxhp_id = " + id);
            while (statement.getResultSet().next()) {
                return new MaxHP(
                        statement.getResultSet().getLong("heroes_maxhp_id"),
                        statement.getResultSet().getLong("hero_id"),
                        statement.getResultSet().getLong("maxhp")
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
            statement.executeUpdate("delete from wow_db.heroes_maxhp where heroes_maxhp_id = " + id);
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
            statement.executeUpdate("insert into wow_db.heroes_maxhp (hero_id, maxhp) values (" + maxHP.getHeroId() + ", " + maxHP.getMaxHP() + ")");
            return maxHP;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public List<MaxHP> findAll() {
        List<MaxHP> maxHPList = null;
        try {
            statement.executeQuery("select * from wow_db.heroes_maxhp");
            while (statement.getResultSet().next()) {
                maxHPList.add(new MaxHP(
                        statement.getResultSet().getLong("heroes_maxhp_id"),
                        statement.getResultSet().getLong("hero_id"),
                        statement.getResultSet().getLong("maxhp")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
        return maxHPList;
    }
}
