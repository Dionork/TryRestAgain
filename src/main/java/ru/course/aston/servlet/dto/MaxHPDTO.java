package ru.course.aston.servlet.dto;

import ru.course.aston.model.Hero;

public class MaxHPDTO {
    private Long maxHPId;
    private Hero hero;
    private Long maxHP;

    public MaxHPDTO(Long maxHPId, Hero hero, Long maxHP) {
        this.maxHPId = maxHPId;
        this.hero = hero;
        this.maxHP = maxHP;
    }

    public Long getMaxHPId() {
        return maxHPId;
    }

    public void setMaxHPId(Long maxHPId) {
        this.maxHPId = maxHPId;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Long getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(Long maxHP) {
        this.maxHP = maxHP;
    }
}
