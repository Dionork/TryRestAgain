package ru.course.aston.db;

import ru.course.aston.utils.PropertiesUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManagerImpl implements ConnectionManager {
    static String driver = "db.driver-class-name";
    String url = "db.url";
    String user = "db.username";
    String password = "db.password";
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
        return DriverManager.getConnection(PropertiesUtils.getProperty(url),
                PropertiesUtils.getProperty(user),
                PropertiesUtils.getProperty(password));

    }

    @Override
    public void closeConnection() {
        ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
        try {
            System.out.println("Закрытие соединения");
            connectionManager.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void loadDriver() {
        try {
            Class.forName(PropertiesUtils.getProperty(driver));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
