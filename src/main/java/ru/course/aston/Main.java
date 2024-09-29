package ru.course.aston;


import ru.course.aston.model.Hero;
import ru.course.aston.model.HeroToFraction;
import ru.course.aston.model.MaxHP;
import ru.course.aston.model.Role;
import ru.course.aston.repository.*;
import ru.course.aston.repository.impl.*;
import ru.course.aston.service.MaxHPService;
import ru.course.aston.service.RoleService;
import ru.course.aston.service.impl.MaxHPServiceImpl;
import ru.course.aston.service.impl.RoleServiceImpl;
import ru.course.aston.servlet.RoleServlet;
import ru.course.aston.servlet.dto.RoleDTO;
import ru.course.aston.servlet.mapper.RoleMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hero newHero = new Hero(15L,"Hero","Hero",3L);
    MaxHP maxHP = new MaxHP(1L,newHero,123L);
        System.out.println(maxHP.getHeroesList());

    }
}