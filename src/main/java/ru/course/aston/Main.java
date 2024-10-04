package ru.course.aston;


import ru.course.aston.model.*;
import ru.course.aston.repository.*;
import ru.course.aston.repository.impl.*;
import ru.course.aston.service.MaxHPService;
import ru.course.aston.service.RoleService;
import ru.course.aston.service.impl.MaxHPServiceImpl;
import ru.course.aston.service.impl.RoleServiceImpl;
import ru.course.aston.servlet.RoleServlet;
import ru.course.aston.servlet.dto.RoleDTO;
import ru.course.aston.servlet.mapper.RoleMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
RoleService roleService = new RoleServiceImpl();
roleService.deleteById(17L);
    }
}