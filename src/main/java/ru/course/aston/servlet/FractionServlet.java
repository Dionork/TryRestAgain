package ru.course.aston.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.service.FractionService;
import ru.course.aston.service.impl.FractionServiceImpl;
import ru.course.aston.servlet.dto.FractionDTO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/fraction/*")
public class FractionServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final FractionService fractionService;

    public FractionServlet() {
        this.fractionService = new FractionServiceImpl();
        this.mapper = new ObjectMapper();
    }

    public FractionServlet(ConnectionManager connectionManager) {
        this.fractionService = new FractionServiceImpl(connectionManager);
        this.mapper = new ObjectMapper();
    }

    private void setJsonHeader(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    @Override
    //Запрос на сервлет
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setJsonHeader(resp);
        String respAnswer; //Ответ сервлета
        try {
            String[] path = req.getPathInfo().split("/");
            if ("all".equals(path[1])) {

                respAnswer = mapper.writeValueAsString(fractionService.findAll());
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                Long id = Long.parseLong(path[1]);
                respAnswer = mapper.writeValueAsString(fractionService.findById(id));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            e.getMessage();
            respAnswer = "Ошибка";
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        PrintWriter out = resp.getWriter();
        out.write(respAnswer);
        out.flush();
    }

    @Override
    //Изменение объекта
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setJsonHeader(resp);
        String respAnswer;
        try {
            String[] path = req.getPathInfo().split("/");
            if ("update".equals(path[1])) {
                String[] params = req.getQueryString().split("=");
                FractionDTO fractionDTO = new FractionDTO(Long.valueOf(params[0]), params[1]);//Парсим
                fractionService.update(fractionDTO); //Сохраняем
                respAnswer = "Изменен";
                resp.setStatus(200); //Успешно
            } else {
                respAnswer = "Ошибка запроса";
                resp.setStatus(400);
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
        String respAnswer;
        setJsonHeader(resp);
        try {
            String[] path = req.getPathInfo().split("/");
            if ("new".equals(path[1])) {
                FractionDTO fractionDTO = new FractionDTO(1L, req.getParameter("name"));
                fractionService.save(fractionDTO); //Сохраняем

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

        String respAnswer;
        try {
            String[] path = req.getPathInfo().split("/");
            if ("delete".equals(path[1])) {
                Long id = Long.parseLong(path[2]);
                fractionService.deleteById(id);
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
