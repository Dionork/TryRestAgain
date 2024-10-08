package ru.course.aston.db;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class ConnectionManagerImplTest {
    static ConnectionManager connectionManager;
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

    }

    @Test
    void getConnection() throws SQLException {
        assertNotNull(connectionManager.getConnection());
    }

    @Test
    void setJdbcUrl() {
        connectionManager.setJdbcUrl(container.getJdbcUrl());
        assertEquals(container.getJdbcUrl(), connectionManager.getJdbcUrl());
    }

    @Test
    void setUsername() {
        connectionManager.setUsername(container.getUsername());
        assertEquals(container.getUsername(), connectionManager.getUsername());
    }

    @Test
    void setPassword() {
        connectionManager.setPassword(container.getPassword());
        assertEquals(container.getPassword(), connectionManager.getPassword());
    }

    @Test
    void setDriver() {
        connectionManager.setDriver(container.getDriverClassName());
        assertEquals(container.getDriverClassName(), connectionManager.getDriver());
    }

    @Test
    void getJdbcUrl() {
        connectionManager.setJdbcUrl(container.getJdbcUrl());
        assertEquals(container.getJdbcUrl(), connectionManager.getJdbcUrl());
    }

    @Test
    void getUsername() {
        connectionManager.setUsername(container.getUsername());
        assertEquals(container.getUsername(), connectionManager.getUsername());
    }

    @Test
    void getPassword() {
        connectionManager.setPassword(container.getPassword());
        assertEquals(container.getPassword(), connectionManager.getPassword());
    }

    @Test
    void getDriver() {
        connectionManager.setDriver(container.getDriverClassName());
        assertEquals(container.getDriverClassName(), connectionManager.getDriver());
    }

    @Test
    void getInstance() {
        assertNotNull(connectionManager);
    }

    @Test
    void closeConnection() {
        connectionManager.closeConnection();
        try {
            assertFalse(connectionManager.getConnection().isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}