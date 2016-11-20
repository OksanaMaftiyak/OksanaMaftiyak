package omaftiyak.javacourse.lab2.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
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

    public static long getLastId(Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement("select lastval()")) {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not get last id", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("libraryapp.jdbc.url"),
                properties.getProperty("libraryapp.jdbc.username"),
                properties.getProperty("libraryapp.jdbc.password")
        );
    }

}
