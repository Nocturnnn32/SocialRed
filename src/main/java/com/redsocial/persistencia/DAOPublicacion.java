package com.redsocial.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.redsocial.modelo.Publicacion;

public class DAOPublicacion {
    private final static String TABLE_NAME = "publicaciones";
    private final static String ID_COLUMN = "id_publicacion";
    private final static String EMAIL_COLUMN = "email";
    private final static String NOMBRE_COLUMN = "nombre";
    private final static String FECHA_COLUMN = "fecha";
    private final static String IMAGEN_COLUMN = "imagen";
    private final static String MENSAJE_COLUMN = "mensaje";

    public static Publicacion insert(Publicacion publicacion) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " (" + EMAIL_COLUMN + ", " + NOMBRE_COLUMN + ", " + FECHA_COLUMN + ", " + IMAGEN_COLUMN + ", " + MENSAJE_COLUMN + ") VALUES (?,?,?,?,?)";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, publicacion.getEmail());
            statement.setString(2, publicacion.getNombre());
            statement.setString(3, publicacion.getFecha());
            statement.setString(4, publicacion.getImagen());
            statement.setString(5, publicacion.getMensaje());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                publicacion.setIdPublicacion(resultSet.getInt(1));
            }
        }
        return publicacion;
    }

    public static void update(Publicacion publicacion) throws SQLException {
        String query = "UPDATE " + TABLE_NAME + " SET " + MENSAJE_COLUMN + " = ?, " + FECHA_COLUMN + " = ? WHERE " + ID_COLUMN + " = ?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, publicacion.getMensaje());
            statement.setString(2, publicacion.getFecha());
            statement.setInt(3, publicacion.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    public static Publicacion select(String idPublicacion) throws SQLException {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = ?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(idPublicacion));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Publicacion(
                            resultSet.getInt(ID_COLUMN),
                            resultSet.getString(EMAIL_COLUMN),
                            resultSet.getString(NOMBRE_COLUMN),
                            resultSet.getString(FECHA_COLUMN),
                            resultSet.getString(IMAGEN_COLUMN),
                            resultSet.getString(MENSAJE_COLUMN)
                    );
                }
            }
        }
        return null;
    }

    public static ArrayList<Publicacion> selectAll() throws SQLException {
        ArrayList<Publicacion> result = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + ID_COLUMN + " DESC";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                result.add(new Publicacion(
                        resultSet.getInt(ID_COLUMN),
                        resultSet.getString(EMAIL_COLUMN),
                        resultSet.getString(NOMBRE_COLUMN),
                        resultSet.getString(FECHA_COLUMN),
                        resultSet.getString(IMAGEN_COLUMN),
                        resultSet.getString(MENSAJE_COLUMN)
                ));
            }
        }
        return result;
    }

    public static void delete(int id) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = ?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}