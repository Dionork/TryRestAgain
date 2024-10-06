package ru.course.aston.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FractionServletTest {
    @Test
    void doGetAll() throws IOException, ServletException {
        String path = "fractions/all";
        FractionServlet fractionServlet = new FractionServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        fractionServlet.doGet(request, response);
        verify(response).setContentType("application/json");
        verify(response).setStatus(200);
        verify(response).getWriter();
    }

    @Test
    void doGetById() throws IOException, ServletException {
        String path = "fractions/1";
        FractionServlet fractionServlet = new FractionServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        fractionServlet.doGet(request, response);
        verify(response).setContentType("application/json");
        verify(response).setStatus(200);
        verify(response).getWriter();
    }

    @Test
    void doGetBadRequest() throws IOException, ServletException {
        FractionServlet fractionServlet = new FractionServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        fractionServlet.doGet(request, response);
        verify(response).setStatus(400);
    }

    @Test
    void doPostBadRequest() throws IOException, ServletException {
        String path = "fractions/asd";
        FractionServlet fractionServlet = new FractionServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path.split("/")[1]).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        fractionServlet.doPost(request, response);
        verify(response).setContentType("application/json");
        verify(response).setStatus(400);
        verify(response).getWriter();
    }

    @Test
    void doDeleteBadRequest() throws IOException, ServletException {
        FractionServlet fractionServlet = new FractionServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        fractionServlet.doDelete(request, response);
        verify(response).setStatus(400);
    }
    @Test
    void doPutBadRequest() throws IOException, ServletException {
        FractionServlet fractionServlet = new FractionServlet();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        fractionServlet.doPut(request, response);
        verify(response).setStatus(400);
    }
}
