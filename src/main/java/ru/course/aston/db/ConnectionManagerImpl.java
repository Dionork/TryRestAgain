package ru.course.aston.db;

import ru.course.aston.utils.PropertiesUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManagerImpl implements ConnectionManager {
    static String driver;
    String url;
    String user;
    String password;
    private static ConnectionManager instance;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManagerImpl();
            loadDriver();
            System.out.println("Новое соединение");
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (url != null && user != null && password != null) {
            return DriverManager.getConnection((url),
                    (user),
                    (password));
        } else {
            return DriverManager.getConnection(PropertiesUtils.getProperty("db.url"),
                    PropertiesUtils.getProperty("db.username"),
                    PropertiesUtils.getProperty("db.password"));
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (url != null && user != null && password != null) {
                DriverManager.getConnection((url),
                        (user),
                        (password)).close();
            }
            else {
            DriverManager.getConnection(PropertiesUtils.getProperty("db.url"),
                    PropertiesUtils.getProperty("db.username"),
                    PropertiesUtils.getProperty("db.password")).close();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            if (driver == null) {
                Class.forName(driver);

            } else {
                Class.forName(PropertiesUtils.getProperty("org.postgresql.Driver"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setJdbcUrl(String url) {
        this.url = url;
    }

    @Override
    public void setUsername(String user) {
        this.user = user;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getJdbcUrl() {
        return url;
    }

    @Override
    public String getUsername() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getDriver() {
        return driver;
    }
}
