package ru.course.aston.servlet.dto;

import org.junit.jupiter.api.Test;
import ru.course.aston.model.Role;
import ru.course.aston.servlet.mapper.RoleMapper;

import static org.junit.jupiter.api.Assertions.*;

class RoleDTOTest {

    @Test
    void getRoleNameId() {
        Role role = new Role(1L, "test");
        RoleDTO roleDTO = RoleMapper.INSTANCE.toDto(role);
        assertEquals(1, roleDTO.getRoleNameId());
    }

    @Test
    void setRoleNameId() {
        Role role = new Role(1L, "test");
        RoleDTO roleDTO = RoleMapper.INSTANCE.toDto(role);
        roleDTO.setRoleNameId(2L);
        assertEquals(2, roleDTO.getRoleNameId());
    }

    @Test
    void getRoleName() {
        Role role = new Role(1L, "test");
        RoleDTO roleDTO = RoleMapper.INSTANCE.toDto(role);
        assertEquals("test", roleDTO.getRoleName());
    }

    @Test
    void setRoleName() {
        Role role = new Role(1L, "test");
        RoleDTO roleDTO = RoleMapper.INSTANCE.toDto(role);
        roleDTO.setRoleName("test2");
        assertEquals("test2", roleDTO.getRoleName());
    }

    @Test
    void testToString() {
        Role role = new Role(1L, "test");
        RoleDTO roleDTO = RoleMapper.INSTANCE.toDto(role);
        assertEquals("RoleDTO{roleNameId=1, roleName='test'}", roleDTO.toString());
    }
}