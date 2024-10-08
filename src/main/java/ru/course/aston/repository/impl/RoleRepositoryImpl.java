package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Role;
import ru.course.aston.repository.RoleRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {
    private ConnectionManager connectionManager;
    public RoleRepositoryImpl() {
        connectionManager = new ConnectionManagerImpl();
    }
    public RoleRepositoryImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    @Override
    public Role findById(Long id) {
        String sql = "SELECT * FROM wow_db.roles WHERE role_name_id = " + id;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Role(resultSet.getLong("role_name_id"),
                        resultSet.getString("role_name"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM wow_db.roles WHERE role_name_id = " + id;
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role save(Role entity) {
        String sql = "INSERT INTO wow_db.roles (role_name) VALUES (?)";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, entity.getRoleName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity = new Role(generatedKeys.getLong(1), entity.getRoleName());
            }

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM wow_db.roles";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(new Role(resultSet.getLong("role_name_id"),
                        resultSet.getString("role_name")));
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Role role) {
        String sql = "UPDATE wow_db.roles SET role_name = ? WHERE role_name_id = ?";
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, role.getRoleName());
            statement.setLong(2, role.getRoleNameId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
