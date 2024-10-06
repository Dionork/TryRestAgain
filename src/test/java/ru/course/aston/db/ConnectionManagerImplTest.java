package ru.course.aston.db;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionManagerImplTest {


    @Test
    void getConnection() throws SQLException {
        ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
        assertNotNull(connectionManager.getConnection());
    }

    @Test
    void closeConnection() {
        ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
        connectionManager.closeConnection();
    }
}