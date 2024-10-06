package ru.course.aston.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HeroServletTest {

    @Test
    void doGetAll() throws IOException, ServletException {
        String path = "hero/all";
        HeroServlet  heroServlet = new HeroServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        heroServlet.doGet(request, response);
        verify(response).setContentType("application/json");
        verify(response).setStatus(200);
        verify(response).getWriter();
    }

    @Test
    void doGetById() throws IOException, ServletException {
        String path = "hero/1";
        HeroServlet  heroServlet = new HeroServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        heroServlet.doGet(request, response);
        verify(response).setContentType("application/json");
        verify(response).setStatus(200);
        verify(response).getWriter();
    }

    @Test
    void doGetBadRequest() throws IOException, ServletException {
        HeroServlet  heroServlet = new HeroServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        heroServlet.doGet(request, response);
        verify(response).setStatus(400);
    }

    @Test
    void doPost() {
    }

    @Test
    void doPut() {
    }

    @Test
    void doDelete() {
    }
}