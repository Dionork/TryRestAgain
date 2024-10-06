package ru.course.aston.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void getRoleNameId() {
        Role role = new Role(1L, "test");
        assertEquals(1, role.getRoleNameId());
    }

    @Test
    void setRoleNameId() {
        Role role = new Role(1L, "test");
        role.setRoleNameId(2L);
        assertEquals(2, role.getRoleNameId());
    }

    @Test
    void getRoleName() {
        Role role = new Role(1L, "test");
        assertEquals("test", role.getRoleName());
    }

    @Test
    void setRoleName() {
        Role role = new Role(1L, "test");
        role.setRoleName("test2");
        assertEquals("test2", role.getRoleName());
    }

    @Test
    void testToString() {
        Role role = new Role(1L, "test");
        assertEquals("Role{roleNameId=1, roleName='test'}", role.toString());
    }
}