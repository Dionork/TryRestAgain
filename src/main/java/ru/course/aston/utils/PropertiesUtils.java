package ru.course.aston.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**Класс для работы со свойствами
 * */
public class PropertiesUtils {
    private static final String PROPERTIES_FILE_NAME = "db.properties";
    private static final Properties properties = new Properties();

    /** Загрузка свойств при запуске
     * */
    static {
        loadProperties();
    }
    /**Метод для получения значения свойства
     * */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    /**Метод для загрузки свойств
     * */
    public static void loadProperties() {
        try (InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

