package ru.course.aston;


import ru.course.aston.model.Role;
import ru.course.aston.repository.FractionRepository;
import ru.course.aston.repository.RoleRepository;
import ru.course.aston.repository.impl.FractionRepositoryImpl;
import ru.course.aston.repository.impl.RoleRepositoryImpl;
import ru.course.aston.service.RoleService;
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
        RoleRepository roleRepository = new RoleRepositoryImpl();
        RoleService roleService = new RoleServiceImpl();
        // roleRepository.save(new Role(1L, "admin"));
        Role role = new Role(1L, "admin");
      //  roleRepository.save(role);
        RoleDTO roleDTO = new RoleDTO(7L,"valera");
        roleService.update(roleDTO);
        System.out.println(roleRepository.findAll());
//        RoleRepository roleRepository = new RoleRepositoryImpl();
//        RoleService roleService = new RoleServiceImpl();
//        roleService.deleteById(4L);
    }
}
