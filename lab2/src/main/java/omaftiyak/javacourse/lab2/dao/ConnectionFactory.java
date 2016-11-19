package omaftiyak.javacourse.lab2.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Creates DB connections
 */
public class ConnectionFactory {

    private static final ConnectionFactory instance = new ConnectionFactory();

    private final Driver driver;
    private final Properties properties;

    private ConnectionFactory() {
        try { 
            properties = new Properties();
            properties.load(new FileInputStream("app.properties"));
            Class<?> driverClass = Class.forName(properties.getProperty("libraryapp.jdbc.driverclass"));
            driver = (Driver) driverClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize ConnectionFactory", e);
        }
    }

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public static Connection getCon() throws SQLException {
        return getInstance().getConnection();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("libraryapp.jdbc.url"),
                properties.getProperty("libraryapp.jdbc.username"),
                properties.getProperty("libraryapp.jdbc.password")
        );
    }

}
