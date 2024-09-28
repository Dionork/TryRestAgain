package ru.course.aston.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.course.aston.model.Hero;
import ru.course.aston.model.MaxHP;
import ru.course.aston.service.HeroService;
import ru.course.aston.service.impl.HeroServiceImpl;
import ru.course.aston.servlet.mapper.HeroMapper;
import ru.course.aston.servlet.mapper.MaxHPMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hero/*")
public class HeroServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final HeroService heroService = new HeroServiceImpl();

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setJsonHeader(resp);
        String json = getJsonHeader(req);
        String respAnswer;
        try {
            String[] path = req.getPathInfo().split("/");
            if ("all".equals(path[1])) {
                respAnswer = mapper.writeValueAsString(heroService.findAll());
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                Long id = Long.parseLong(path[1]);
                respAnswer = mapper.writeValueAsString(heroService.findById(id));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            respAnswer = "{\"error\": \"Bad request\"}";
        }
        PrintWriter out = resp.getWriter();
        out.println(respAnswer);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String respAnswer = "";
        setJsonHeader(resp);
        String json = getJsonHeader(req);
        try {
            String[] path = req.getPathInfo().split("/");
            if ("update".equals(path[1])) {
                String id = req.getParameter("id");
                String name = req.getParameter("name");
                String lastName = req.getParameter("lastName");
                String roleNameId = req.getParameter("roleId");
                Hero hero = new Hero(Long.parseLong(id),
                        String.valueOf(name),
                        String.valueOf(lastName),
                        Long.parseLong(roleNameId));
                heroService.update(HeroMapper.INSTANCE.toDTO(hero));
                respAnswer = "Изменено";
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                respAnswer = "Error";
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            respAnswer = "{\"error\": \"Bad request\"}";
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        PrintWriter out = resp.getWriter();
        out.println(respAnswer);
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String respAnswer = "";
        setJsonHeader(resp);
        String json = getJsonHeader(req);
        try {
            String[] path = req.getPathInfo().split("/");
            if ("new".equals(path[1])) {
                String id = req.getParameter("id");
                String name = req.getParameter("name");
                String lastName = req.getParameter("lastName");
                String roleNameId = req.getParameter("roleId");
                Hero hero = new Hero(Long.parseLong(id),
                        String.valueOf(name),
                        String.valueOf(lastName),
                        Long.parseLong(roleNameId));
                heroService.save(HeroMapper.INSTANCE.toDTO(hero));
                respAnswer = "Добавлено";
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                respAnswer = "Error";
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (Exception e) {
            respAnswer = "{\"error\": \"Bad request\"}";
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        PrintWriter out = resp.getWriter();
        out.println(respAnswer);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String respAnswer = "";
        try {
            String[] path = req.getPathInfo().split("/");
            if ("delete".equals(path[1])) {
                Long id = Long.parseLong(path[2]);
                heroService.deleteById(id);
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
