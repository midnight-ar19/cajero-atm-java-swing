package com.mycompany.cajeroautomatico.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final String url;
    private final String user;
    private final String password;
    private final String driver;

    private DatabaseConnection() {
        Properties props = new Properties();
        try (InputStream input = DatabaseConnection.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("db.properties no encontrado");
            }
            props.load(input);
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
            this.driver = props.getProperty("db.driver");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar config: " + e.getMessage(), e);
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Conexion exitosa a: " + url);
            System.out.println("Base de datos: atm_db");
            System.out.println("Usuario: " + user);
        } catch (SQLException e) {
            throw new RuntimeException("Error en conexion: " + e.getMessage(), e);
        }
    }
}