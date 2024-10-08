package ru.course.aston.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.model.Fraction;
import ru.course.aston.model.Hero;
import ru.course.aston.model.HeroToFraction;

import ru.course.aston.service.FractionService;
import ru.course.aston.service.HeroService;
import ru.course.aston.service.HeroToFractionService;
import ru.course.aston.service.impl.FractionServiceImpl;
import ru.course.aston.service.impl.HeroServiceImpl;
import ru.course.aston.service.impl.HeroToFractionServiceImpl;

import ru.course.aston.servlet.mapper.FractionMapper;
import ru.course.aston.servlet.mapper.HeroMapper;
import ru.course.aston.servlet.mapper.HeroToFractionMapper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/herotofraction/*")
public class HeroToFractionServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final HeroToFractionService heroToFractionService;
    private HeroService heroService ;
    private FractionService fractionService;

    public HeroToFractionServlet(ConnectionManager connectionManager) {
        heroToFractionService = new HeroToFractionServiceImpl(connectionManager);
        heroService = new HeroServiceImpl(connectionManager);
        fractionService = new FractionServiceImpl(connectionManager);
        mapper = new ObjectMapper();
    }
    public HeroToFractionServlet() {
        heroToFractionService = new HeroToFractionServiceImpl();
        heroService = new HeroServiceImpl();
        fractionService = new FractionServiceImpl();
        mapper = new ObjectMapper();
    }
    private void setJsonHeader(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setJsonHeader(resp);
        String respAnswer;
        try {
            String[] path = req.getPathInfo().split("/");
            if ("all".equals(path[1])) {
                respAnswer = mapper.writeValueAsString(heroToFractionService.findAll());
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                Long id = Long.parseLong(path[1]);
                respAnswer = mapper.writeValueAsString(heroToFractionService.findById(id));
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
        try {
            String[] path = req.getPathInfo().split("/");
            if ("update".equals(path[1])) {
                String id = req.getParameter("heroToFractionId");
                String fractionId = req.getParameter("fractionId");
                String heroId = req.getParameter("heroId");
                Hero hero = HeroMapper.INSTANCE.toModel(heroService.findById(Long.parseLong(heroId)));
                Fraction fraction = FractionMapper.INSTANCE.toModel(fractionService.findById(Long.parseLong(fractionId)));
                HeroToFraction heroToFraction = new HeroToFraction(Long.parseLong(id), hero, fraction);
                heroToFractionService.update(HeroToFractionMapper.INSTANCE.toDTO(heroToFraction));
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
        try {
            String[] path = req.getPathInfo().split("/");
            if ("new".equals(path[1])) {
                String id = req.getParameter("heroToFractionId");
                String fractionId = req.getParameter("fractionId");
                String heroId = req.getParameter("heroId");
                Hero hero = HeroMapper.INSTANCE.toModel(heroService.findById(Long.parseLong(heroId)));
                Fraction fraction = FractionMapper.INSTANCE.toModel(fractionService.findById(Long.parseLong(fractionId)));
                HeroToFraction heroToFraction = new HeroToFraction(Long.parseLong(id), hero, fraction);
                heroToFractionService.save(HeroToFractionMapper.INSTANCE.toDTO(heroToFraction));
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
                heroToFractionService.deleteById(id);
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
