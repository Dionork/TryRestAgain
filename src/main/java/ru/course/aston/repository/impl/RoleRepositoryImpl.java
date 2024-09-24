package ru.course.aston.repository.impl;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Role;
import ru.course.aston.repository.RoleRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleRepositoryImpl implements RoleRepository {
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
    public Role findById(Long id) {
        try {
            statement.executeQuery("SELECT * FROM wow_db.roles WHERE role_name_id = " + id);
            if (statement.getResultSet().next()) {
                return new Role(
                        statement.getResultSet().getLong("role_name_id"),
                        statement.getResultSet().getString("role_name")
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
            statement.executeUpdate("DELETE FROM wow_db.roles WHERE role_name_id = " + id);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public Role save(Role entity) {
        try {
            statement.getGeneratedKeys();
            statement.executeUpdate("INSERT INTO wow_db.roles (role_name_id, role_name) VALUES ("
                    + entity.getRoleNameId() + ", '" + entity.getRoleName() + "')");
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try {
            statement.executeQuery("SELECT * FROM wow_db.roles");
            while (statement.getResultSet().next()) {
                Role role = new Role(
                        statement.getResultSet().getLong("role_name_id"),
                        statement.getResultSet().getString("role_name"
                        ));
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            connectionManager.closeConnection();
        }
        return roles;
    }
}
