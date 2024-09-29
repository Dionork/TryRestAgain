package ru.course.aston.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.course.aston.service.RoleService;
import ru.course.aston.service.impl.RoleServiceImpl;
import ru.course.aston.servlet.dto.RoleDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/role/*"})
public class RoleServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final RoleService roleService = new RoleServiceImpl();

    private void setJsonHeader(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    private static String getJsonHeader(HttpServletRequest req) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    @Override
    //Запрос на сервлет
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setJsonHeader(resp);
        String respAnswer; //Ответ сервлета
        try {
            String[] path = req.getPathInfo().split("/");
            if ("all".equals(path[1])) {
                respAnswer = mapper.writeValueAsString(roleService.findAll());
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                Long id = Long.parseLong(path[1]);
                respAnswer = mapper.writeValueAsString(roleService.findById(id));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            e.getMessage();
            respAnswer = "Ошибка";
        }
        PrintWriter out = resp.getWriter();
        out.write(respAnswer);
        out.flush();
    }

    @Override
    //Изменение объекта
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String respAnswer = "";
        try {
            String[] path = req.getPathInfo().split("/");
            if ("update".equals(path[1])) {
                String[] params = req.getQueryString().split("=");
                RoleDTO roleDTO = new RoleDTO(Long.valueOf(params[0]), params[1]);//Парсим
                roleService.update(roleDTO); //Сохраняем
                respAnswer = "Изменен";
                resp.setStatus(HttpServletResponse.SC_OK); //Успешно
            } else {
                respAnswer = "Ошибка запроса";
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); //Успешно}
            }
        } catch (Exception e) {
            e.getMessage();
            respAnswer = "Ошибка";
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        PrintWriter out = resp.getWriter();
        out.write(respAnswer); //Успешно
        out.flush(); //Ответ сервлета
    }


    @Override
    //Новый объект
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String respAnswer = "";
        setJsonHeader(resp);
        String json = getJsonHeader(req);
        try {
            String[] path = req.getPathInfo().split("/");
            if ("new".equals(path[1])) {
                RoleDTO roleDTO = new RoleDTO(1L, req.getParameter("name"));
                roleService.save(roleDTO); //Сохраняем

                respAnswer = "Создан";
                resp.setStatus(HttpServletResponse.SC_CREATED); //Успешно
            } else {
                respAnswer = "Ошибка запроса";
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.getMessage();
            respAnswer = "Ошибка";
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        PrintWriter out = resp.getWriter();
        out.write(respAnswer); //Успешно
        out.flush(); //Ответ сервлета
    }

    @Override
    //Удаление объекта
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String respAnswer = "";
        try {
            String[] path = req.getPathInfo().split("/");
            if ("delete".equals(path[1])) {
                Long id = Long.parseLong(path[2]);
                roleService.deleteById(id);
                respAnswer = "Удален";
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                respAnswer = "Ошибка запроса";
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.getMessage();
            respAnswer = "Ошибка";
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        PrintWriter out = resp.getWriter();
        out.write(respAnswer); //Успешно
        out.flush(); //Ответ сервлета
    }
}
