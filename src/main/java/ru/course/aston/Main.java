package ru.course.aston;


import ru.course.aston.model.MaxHP;
import ru.course.aston.model.Role;
import ru.course.aston.repository.FractionRepository;
import ru.course.aston.repository.MaxHPRepository;
import ru.course.aston.repository.RoleRepository;
import ru.course.aston.repository.impl.FractionRepositoryImpl;
import ru.course.aston.repository.impl.MaxHPRepositoryImpl;
import ru.course.aston.repository.impl.RoleRepositoryImpl;
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
               MaxHPRepository maxHPRepository = new MaxHPRepositoryImpl();
        MaxHPService maxHPService = new MaxHPServiceImpl();
        System.out.println(maxHPService.findById(2L));


    }
}
