package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.repository.FractionRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FractionRepositoryImpl implements FractionRepository {
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
    public Fraction findById(Long id) {
        try {
            statement.executeQuery("SELECT * FROM wow_db.fractions WHERE fraction_id = " + id);
            if (statement.getResultSet().next()) {
                Fraction fraction = new Fraction(
                        statement.getResultSet().getLong("fraction_id"),
                        statement.getResultSet().getString("fraction_name")
                );
                return fraction;
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
            statement.executeUpdate("DELETE FROM wow_db.fractions WHERE fraction_id = " + id);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public Fraction save(Fraction fraction) {
        try {
            statement.executeUpdate("INSERT INTO wow_db.fractions (fraction_id,fraction_name) VALUES (" +
                    fraction.getFractionId()+", '"+ fraction.getFractionName() + "')");
            return fraction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public List<Fraction> findAll() {
        List<Fraction> fractionList = new ArrayList<>();
        try {
            statement.executeQuery("SELECT * FROM wow_db.fractions");
            while (statement.getResultSet().next()) {
                Fraction fraction = new Fraction(
                        statement.getResultSet().getLong("fraction_id"),
                        statement.getResultSet().getString("fraction_name")
                );
                fractionList.add(fraction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
        return fractionList;
    }
}
