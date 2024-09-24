package ru.course.aston.servlet.dto;

public class RoleDTO {
    private Long roleNameId;
    private String roleName;

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
}
