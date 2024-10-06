package ru.course.aston.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroToFractionTest {

    @Test
    void getHeroToFractionId() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        assertEquals(1L, heroToFraction.getHeroToFractionId());
    }

    @Test
    void setHeroToFractionId() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        heroToFraction.setHeroToFractionId(2L);
        assertEquals(2L, heroToFraction.getHeroToFractionId());
    }

    @Test
    void getHero() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        assertEquals(hero, heroToFraction.getHero());
    }

    @Test
    void setHero() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        heroToFraction.setHero(new Hero(2L, "name", "surname",1L));
        assertEquals(2, heroToFraction.getHero().getHeroId());
    }

    @Test
    void getFraction() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        assertEquals(fraction, heroToFraction.getFraction());
    }

    @Test
    void setFraction() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        heroToFraction.setFraction(new Fraction(2L,"fractionName"));
        assertEquals(2L, heroToFraction.getFraction().getFractionId());
    }

}