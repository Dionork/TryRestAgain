package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Role;
import ru.course.aston.repository.RoleRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {
    private ConnectionManager connectionManager = new ConnectionManagerImpl();
    private PreparedStatement statement;


    @Override
    public Role findById(Long id) {
        try {
            String sql = "SELECT * FROM wow_db.roles WHERE role_name_id = " + id;
            statement = connectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Role(resultSet.getLong("role_name_id"),
                        resultSet.getString("role_name"));
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
        String sql = "DELETE FROM wow_db.roles WHERE role_name_id = " + id;
        try {
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
    public Role save(Role entity) {
        String sql = "INSERT INTO wow_db.roles (role_name) VALUES (?)";
        try {
            statement = connectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getRoleName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity = new Role(generatedKeys.getLong(1), entity.getRoleName());
            }

            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT * FROM wow_db.roles";
            statement = connectionManager.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roles.add(new Role(resultSet.getLong("role_name_id"),
                        resultSet.getString("role_name")));
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
    }
@Override
    public void update(Role role) {
        String sql = "UPDATE wow_db.roles SET role_name = ? WHERE role_name_id = ?";
        try {
            statement = connectionManager.getConnection().prepareStatement(sql);
            statement.setString(1, role.getRoleName());
            statement.setLong(2, role.getRoleNameId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
    }
}
