package ru.course.aston.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@Testcontainers
@ExtendWith(MockitoExtension.class)
class FractionServletTest {
    static FractionServlet fractionServlet;
    static ConnectionManager connectionManager;
    private static JdbcDatabaseDelegate jdbcDatabaseDelegate;
    @Container
    public static final PostgreSQLContainer container = new PostgreSQLContainer<>("postgres:14-alpine");

    @BeforeAll
    public static void startContainer() {
        container.start();
        connectionManager = new ConnectionManagerImpl();
        connectionManager.setDriver(container.getDriverClassName());
        connectionManager.setJdbcUrl(container.getJdbcUrl());
        connectionManager.setUsername(container.getUsername());
        connectionManager.setPassword(container.getPassword());
        jdbcDatabaseDelegate = new JdbcDatabaseDelegate(container, "");
        fractionServlet = new FractionServlet(connectionManager);
    }

    @BeforeEach
    public void initSchema() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "sql/schema.sql");

    }
    @Test
    void doGetAll() throws IOException, ServletException {
        String path = "fractions/all";
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
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        fractionServlet.doGet(request, response);
        verify(response).setStatus(400);
    }

    @Test
    void doPostBadRequest() throws IOException, ServletException {
        String path = "fractions/asd";
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
        String path = "fractions/asd";
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        fractionServlet.doDelete(request, response);
        verify(response).setStatus(400);
    }
    @Test
    void doPutBadRequest() throws IOException, ServletException {
        String path = "fractions/asd";
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        fractionServlet.doPut(request, response);
        verify(response).setContentType("application/json");
        verify(response).setStatus(400);
        verify(response).getWriter();
    }
    @Test
    void constructor() {
        FractionServlet fractionServlet = new FractionServlet();
        assertNotNull(fractionServlet);
    }
}
