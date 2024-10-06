package ru.course.aston.servlet.dto;

import org.junit.jupiter.api.Test;
import ru.course.aston.model.Fraction;
import ru.course.aston.model.Hero;
import ru.course.aston.model.HeroToFraction;
import ru.course.aston.servlet.mapper.HeroToFractionMapper;

import static org.junit.jupiter.api.Assertions.*;

class HeroToFractionDTOTest {

    @Test
    void getHeroToFractionId() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        HeroToFractionDTO heroToFractionDTO = HeroToFractionMapper.INSTANCE.toDTO(heroToFraction);
        assertEquals(heroToFraction.getHeroToFractionId(),heroToFractionDTO.getHeroToFractionId());
    }

    @Test
    void setHeroToFractionId() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        HeroToFractionDTO heroToFractionDTO = HeroToFractionMapper.INSTANCE.toDTO(heroToFraction);
        heroToFractionDTO.setHeroToFractionId(2L);
        assertEquals(2L,heroToFractionDTO.getHeroToFractionId());
    }

    @Test
    void getHero() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        HeroToFractionDTO heroToFractionDTO = HeroToFractionMapper.INSTANCE.toDTO(heroToFraction);
        assertEquals(hero,heroToFractionDTO.getHero());
    }

    @Test
    void setHero() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        HeroToFractionDTO heroToFractionDTO = HeroToFractionMapper.INSTANCE.toDTO(heroToFraction);
        Hero hero1 = new Hero(2L, "name1", "surname1",1L);
        heroToFractionDTO.setHero(hero1);
        assertEquals(hero1,heroToFractionDTO.getHero());

    }

    @Test
    void getFraction() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        HeroToFractionDTO heroToFractionDTO = HeroToFractionMapper.INSTANCE.toDTO(heroToFraction);
        Fraction fraction1 = new Fraction(2L,"fractionName1");
        heroToFractionDTO.setFraction(fraction1);
        assertEquals(fraction1,heroToFractionDTO.getFraction());
    }

    @Test
    void setFraction() {
        Hero hero = new Hero(1L, "name", "surname",1L);
        Fraction fraction = new Fraction(1L,"fractionName");
        HeroToFraction heroToFraction = new HeroToFraction(1L,hero,fraction);
        HeroToFractionDTO heroToFractionDTO = HeroToFractionMapper.INSTANCE.toDTO(heroToFraction);
        Fraction fraction1 = new Fraction(2L,"fractionName1");
        heroToFractionDTO.setFraction(fraction1);
        assertEquals(fraction1,heroToFractionDTO.getFraction());
    }
}