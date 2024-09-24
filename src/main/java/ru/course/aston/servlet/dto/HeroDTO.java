package ru.course.aston.servlet.dto;

public class HeroDTO {
    private String heroName;
    private String heroLastName;
    private Long roleNameId;

    public HeroDTO(String heroName, String heroLastName, Long roleNameId) {
        this.heroName = heroName;
        this.heroLastName = heroLastName;
        this.roleNameId = roleNameId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroLastName() {
        return heroLastName;
    }

    public void setHeroLastName(String heroLastName) {
        this.heroLastName = heroLastName;
    }

    public Long getRoleNameId() {
        return roleNameId;
    }

    public void setRoleNameId(Long roleNameId) {
        this.roleNameId = roleNameId;
    }
}
