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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@Testcontainers
@ExtendWith(MockitoExtension .class)
class RoleServletTest {
    static RoleServlet roleServlet;
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
        roleServlet = new RoleServlet(connectionManager);
    }

    @BeforeEach
    public void initSchema() {
        ScriptUtils.runInitScript(jdbcDatabaseDelegate, "sql/schema.sql");

    }
    @Test
    void doGetAll() throws IOException, ServletException {
        String path = "role/all";
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        roleServlet.doGet(request, response);
        verify(response).setContentType("application/json");
        verify(response).setStatus(200);
        verify(response).getWriter();
    }

    @Test
    void doGetById() throws IOException, ServletException {
        String path = "role/1";
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        roleServlet.doGet(request, response);
        verify(response).setContentType("application/json");
        verify(response).setStatus(200);
        verify(response).getWriter();
    }

    @Test
    void doGetBadRequest() throws IOException, ServletException {
        String path = "role/qwe";
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        roleServlet.doGet(request, response);
        verify(response).setStatus(400);
    }

    @Test
    void doPostBadRequest() throws IOException, ServletException  {
        String path = "role/asd";
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        roleServlet.doPost(request, response);
        verify(response).setStatus(400);
    }

    @Test
    void doPutBadRequest() throws IOException, ServletException {
        String path = "role/asd";
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        roleServlet.doPut(request, response);
        verify(response).setStatus(400);
    }

    @Test
    void doDeleteBadRequest() throws IOException, ServletException  {
        String path = "role/asd";
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        Mockito.doReturn(path).when(request).getPathInfo();
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        roleServlet.doDelete(request, response);
        verify(response).setStatus(400);
    }
    @Test
    void constructor() {
        RoleServlet roleServlet = new RoleServlet();
        assertNotNull(roleServlet);
    }
}