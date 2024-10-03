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
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("docker build -t db");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            process.waitFor(60, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(line);

        }

    }
}