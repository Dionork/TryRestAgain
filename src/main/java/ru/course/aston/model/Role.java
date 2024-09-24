package ru.course.aston.model;

public class Role {
private Long roleNameId;
private String roleName;

    public Role(Long roleNameId, String roleName) {
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
        return "Role{" +
                "roleNameId=" + roleNameId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
