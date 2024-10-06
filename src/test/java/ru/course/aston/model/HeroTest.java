package ru.course.aston.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void getHeroLastName() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        assertEquals("Ivanov", hero.getHeroLastName());
    }

    @Test
    void setHeroLastName() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        hero.setHeroLastName("Petrov");
        assertEquals("Petrov", hero.getHeroLastName());
    }

    @Test
    void getHeroId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        assertEquals(1L, hero.getHeroId());
    }

    @Test
    void setHeroId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        hero.setHeroId(2L);
        assertEquals(2L, hero.getHeroId());
    }

    @Test
    void getHeroName() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        assertEquals("Ivan", hero.getHeroName());
    }

    @Test
    void setHeroName() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        hero.setHeroName("Petr");
        assertEquals("Petr", hero.getHeroName());
    }

    @Test
    void getRoleNameId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        assertEquals(1L, hero.getRoleNameId());
    }

    @Test
    void setRoleNameId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        hero.setRoleNameId(2L);
        assertEquals(2L, hero.getRoleNameId());
    }

    @Test
    void testToString() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        assertEquals("Hero{heroId=1, heroName='Ivan', heroLastName='Ivanov', roleNameId=1}", hero.toString());
    }
}