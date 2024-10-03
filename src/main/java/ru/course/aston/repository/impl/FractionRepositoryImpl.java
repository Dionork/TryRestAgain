package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.repository.FractionRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FractionRepositoryImpl implements FractionRepository {
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private PreparedStatement statement;


    @Override
    public Fraction findById(Long id) {
        try {
            String sql = "SELECT * FROM wow_db.fractions WHERE fraction_id =" + id;
            statement = connectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return new Fraction(resultSet.getLong("fraction_id"),
                        resultSet.getString("fraction_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            String sql = "DELETE FROM wow_db.fractions WHERE fraction_id =?";
            statement = connectionManager.getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Fraction save(Fraction fraction) {
        try {
            String sql = "INSERT INTO wow_db.fractions (fraction_name) VALUES (?)";
            statement = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, fraction.getFractionName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                fraction = new Fraction(generatedKeys.getLong(1),
                        fraction.getFractionName());
            }
            return fraction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Fraction> findAll() {
        List<Fraction> fractionList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM wow_db.fractions";
            statement = connectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                fractionList.add(new Fraction(resultSet.getLong("fraction_id"),
                        resultSet.getString("fraction_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fractionList;
    }

    @Override
    public void update(Fraction fraction) {
        try {
            String sql = "UPDATE wow_db.fractions SET fraction_name = ? WHERE fraction_id = ?";
            statement = connectionManager.getConnection().prepareStatement(sql);
            statement.setString(1, fraction.getFractionName());
            statement.setLong(2, fraction.getFractionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
