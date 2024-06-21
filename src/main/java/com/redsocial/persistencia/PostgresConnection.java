package com.redsocial.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    
    private static final String URL = "jdbc:postgresql://ep-winter-wood-a5qbq7o4-pooler.us-east-2.aws.neon.tech/social";
    
//jdbc:postgresql://ep-winter-wood-a5qbq7o4-pooler.us-east-2.aws.neon.tech/social?user=social_owner&password=w8WIsnzSGK2A&sslmode=require
    private static final String USER = "social_owner";
    private static final String PASSWORD = "w8WIsnzSGK2A";

    static {
        try {
            // Registrar el driver de PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al registrar el driver de PostgreSQL", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
