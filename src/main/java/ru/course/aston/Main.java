package ru.course.aston;

import ru.course.aston.db.ConnectionManager;
import ru.course.aston.db.ConnectionManagerImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManagerImpl();
        try {
            connectionManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
