package ru.course.aston.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.course.aston.InitSchemeSql;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;
import ru.course.aston.model.Hero;
import ru.course.aston.model.Role;
import ru.course.aston.repository.RoleRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

class RoleRepositoryImplTest {
    RoleRepository roleRepository = new RoleRepositoryImpl();
    @BeforeAll
    public static void initSQL() {
        ConnectionManager connectionManager = new ConnectionManagerImpl();
        PreparedStatement statement;

        try {
            statement = connectionManager.getConnection().prepareStatement(InitSchemeSql.INIT_SCHEME_SQL);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionManager.closeConnection();
        }
    }
    @Test
    void findById() {
        Assertions.assertEquals("newRoleName",roleRepository.findById(1L).getRoleName());

    }

    @Test
    void deleteById() {
        roleRepository.deleteById(4L);
        Optional<Role> result = Optional.ofNullable(roleRepository.findById(4L));
        Assertions.assertFalse(result.isPresent());;
    }

    @Test
    void save() {
        Role role = new Role(4L,"roleName");
        roleRepository.save(role);
        Optional<Role> result = Optional.ofNullable(roleRepository.findById(role.getRoleNameId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(role.getRoleName(),result.get().getRoleName());

    }

    @Test
    void findAll() {
        Assertions.assertEquals(3,roleRepository.findAll().size());
    }

    @Test
    void update() {
        Role role = new Role(1L,"newRoleName");
        roleRepository.update(role);
        Optional<Role> result = Optional.ofNullable(roleRepository.findById(role.getRoleNameId()));
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(role.getRoleName(),result.get().getRoleName());
    }
}