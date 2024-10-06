package ru.course.aston.servlet.dto;

import org.junit.jupiter.api.Test;
import ru.course.aston.model.Hero;
import ru.course.aston.servlet.mapper.HeroMapper;

import static org.junit.jupiter.api.Assertions.*;

class HeroDTOTest {

    @Test
    void getHeroLastName() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        HeroDTO heroDTO = HeroMapper.INSTANCE.toDTO(hero);
        assertEquals("Ivanov", heroDTO.getHeroLastName());
    }

    @Test
    void setHeroLastName() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        HeroDTO heroDTO = HeroMapper.INSTANCE.toDTO(hero);
        heroDTO.setHeroLastName("Vasya");
        assertEquals("Vasya", heroDTO.getHeroLastName());

    }

    @Test
    void getHeroId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        HeroDTO heroDTO = HeroMapper.INSTANCE.toDTO(hero);
        assertEquals(1L, heroDTO.getHeroId());
    }

    @Test
    void setHeroId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        HeroDTO heroDTO = HeroMapper.INSTANCE.toDTO(hero);
        heroDTO.setHeroId(2L);
        assertEquals(2L, heroDTO.getHeroId());
    }

    @Test
    void getHeroName() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        HeroDTO heroDTO = HeroMapper.INSTANCE.toDTO(hero);
        assertEquals("Ivan", heroDTO.getHeroName());
    }

    @Test
    void setHeroName() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        HeroDTO heroDTO = HeroMapper.INSTANCE.toDTO(hero);
        heroDTO.setHeroName("Vasya");
        assertEquals("Vasya", heroDTO.getHeroName());
    }

    @Test
    void getRoleNameId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        HeroDTO heroDTO = HeroMapper.INSTANCE.toDTO(hero);
        assertEquals(1L, heroDTO.getRoleNameId());
    }

    @Test
    void setRoleNameId() {
        Hero hero = new Hero(1L, "Ivan", "Ivanov",1L);
        HeroDTO heroDTO = HeroMapper.INSTANCE.toDTO(hero);
        heroDTO.setRoleNameId(2L);
        assertEquals(2L, heroDTO.getRoleNameId());
    }
}