package ru.course.aston.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    Connection getConnection() throws SQLException;

    void closeConnection();

    void setJdbcUrl(String s);

    void setUsername(String username);

    void setPassword(String password);
    void setDriver(String driver);

    String getJdbcUrl();

    String getUsername();

    String getPassword();

    String getDriver();
}
