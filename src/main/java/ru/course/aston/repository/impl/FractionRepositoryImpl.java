package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Fraction;
import ru.course.aston.repository.FractionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FractionRepositoryImpl implements FractionRepository  {
    private ConnectionManager connectionManager;
    public FractionRepositoryImpl (){
        connectionManager = new ConnectionManagerImpl();
    }
    public FractionRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager ;
    }

    @Override
    public Fraction findById(Long id) {
        String sql = "SELECT * FROM wow_db.fractions WHERE fraction_id =" + id;
        if (id == null) {
            throw new NullPointerException();
        }
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return new Fraction(resultSet.getLong("fraction_id"),
                        resultSet.getString("fraction_name"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM wow_db.fractions WHERE fraction_id =?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Fraction save(Fraction fraction) {
        String sql = "INSERT INTO wow_db.fractions (fraction_name) VALUES (?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
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
        String sql = "SELECT * FROM wow_db.fractions";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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
        String sql = "UPDATE wow_db.fractions SET fraction_name = ? WHERE fraction_id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fraction.getFractionName());
            statement.setLong(2, fraction.getFractionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
