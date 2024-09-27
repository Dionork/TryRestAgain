package ru.course.aston.servlet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat
public class RoleDTO {
    Long roleNameId;
    String roleName;

    public RoleDTO(Long roleNameId, String roleName) {
        this.roleNameId = roleNameId;
        this.roleName = roleName;
    }
    public Long getRoleNameId() {
        return roleNameId;
    }


    public void setRoleNameId(Long roleNameId) {
        this.roleNameId = roleNameId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
               "roleNameId=" + roleNameId +
               ", roleName='" + roleName + '\'' +
               '}';
    }
}
