package ru.course.aston.model;

import ru.course.aston.repository.HeroRepository;


import java.util.*;

/**
 * many to one
 * hero to maxHP
 */
public class MaxHP {
    private HeroRepository heroRepository;
    private Long maxHPId;
    private Hero hero;
    private Long maxHP;
    private List<Hero> heroes;

    public MaxHP(Long maxHPId, Hero hero, Long maxHP) {
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

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    @Override
    public String toString() {
        return "MaxHP{" +
               "maxHPId=" + maxHPId +
               ", hero=" + hero +
               ", maxHP=" + maxHP +
               '}';
    }
}
