package ru.course.aston.db;

import ru.course.aston.utils.PropertiesUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManagerImpl implements ConnectionManager {
    String driver = "db.driver-class-name";
    String url = "db.url";
    String user = "db.username";
    String password = "db.password";

    /**
     * @return Подключение к БД*/
    @Override
    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(PropertiesUtils.getProperty(driver));
            connection = DriverManager.getConnection(
                    PropertiesUtils.getProperty(url),
                    PropertiesUtils.getProperty(user),
                    PropertiesUtils.getProperty(password));
            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connection failed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return connection;
    }

    @Override
    public void closeConnection() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
