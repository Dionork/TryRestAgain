package ru.course.aston.model;

public class Hero {
private Long heroId;
private String heroName;
private String heroLastName;
private Long roleNameId;

    public Hero(Long heroId, String heroName,String heroLastName,Long roleNameId) {
        this.heroId = heroId;
        this.heroName = heroName;
        this.heroLastName = heroLastName;
        this.roleNameId = roleNameId;
    }

    public String getHeroLastName() {
        return heroLastName;
    }

    public void setHeroLastName(String heroLastName) {
        this.heroLastName = heroLastName;
    }

    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Long getRoleNameId() {
        return roleNameId;
    }

    public void setRoleNameId(Long roleNameId) {
        this.roleNameId = roleNameId;
    }
}
