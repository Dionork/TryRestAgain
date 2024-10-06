package ru.course.aston.servlet.dto;

import org.junit.jupiter.api.Test;
import ru.course.aston.model.Hero;
import ru.course.aston.model.MaxHP;
import ru.course.aston.servlet.mapper.MaxHPMapper;

import static org.junit.jupiter.api.Assertions.*;

class MaxHPDTOTest {

    @Test
    void getMaxHPId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        MaxHPDTO maxHPDTO = MaxHPMapper.INSTANCE.toDTO(maxHP);
        assertEquals(1, maxHPDTO.getMaxHPId());
    }

    @Test
    void setMaxHPId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        MaxHPDTO maxHPDTO = MaxHPMapper.INSTANCE.toDTO(maxHP);
        maxHPDTO.setMaxHPId(2L);
        assertEquals(2, maxHPDTO.getMaxHPId());
    }

    @Test
    void getHero() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        MaxHPDTO maxHPDTO = MaxHPMapper.INSTANCE.toDTO(maxHP);
        assertEquals(hero, maxHPDTO.getHero());
    }

    @Test
    void setHero() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        MaxHPDTO maxHPDTO = MaxHPMapper.INSTANCE.toDTO(maxHP);
        Hero hero1 = new Hero(2L, "Petr", "Petrov", 2L);
        maxHPDTO.setHero(hero1);
        assertEquals(hero1, maxHPDTO.getHero());
    }

    @Test
    void getMaxHP() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        MaxHPDTO maxHPDTO = MaxHPMapper.INSTANCE.toDTO(maxHP);
        assertEquals(1, maxHPDTO.getMaxHP());
    }

    @Test
    void setMaxHP() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov", 1L);
        MaxHP maxHP = new MaxHP(1L, hero, 1L);
        MaxHPDTO maxHPDTO = MaxHPMapper.INSTANCE.toDTO(maxHP);
        maxHPDTO.setMaxHP(2L);
        assertEquals(2, maxHPDTO.getMaxHP());
    }
}