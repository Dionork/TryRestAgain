package ru.course.aston.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxHPTest {

    @Test
    void getMaxHPId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        assertEquals(1, maxHP.getMaxHPId());
    }

    @Test
    void setMaxHPId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        maxHP.setMaxHPId(2L);
        assertEquals(2, maxHP.getMaxHPId());
    }

    @Test
    void getHero() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        assertEquals(hero, maxHP.getHero());
    }

    @Test
    void setHero() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        Hero hero2 = new Hero(2L, "Ivan", "Ivanov", 1L);
        maxHP.setHero(hero2);
        assertEquals(hero2, maxHP.getHero());
    }

    @Test
    void getMaxHP() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        assertEquals(1, maxHP.getMaxHP());
    }

    @Test
    void setMaxHP() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        maxHP.setMaxHP(2L);
        assertEquals(2, maxHP.getMaxHP());
    }

    @Test
    void testToString() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        assertEquals("MaxHP{maxHPId=1, hero=Hero{heroId=1, heroName='Ivan', heroLastName='Ivanov', roleNameId=1}, maxHP=1}", maxHP.toString());
    }

    @Test
    void setHeroes() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        maxHP.setHeroes(heroes);
        assertEquals(hero, heroes.get(0));
    }
}