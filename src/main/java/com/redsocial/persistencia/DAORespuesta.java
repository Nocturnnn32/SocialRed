package com.redsocial.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.redsocial.modelo.Respuesta;

public class DAORespuesta {
    private final static String TABLE_NAME = "respuestas";
    private final static String EMAIL_COLUMN = "email";
    private final static String FECHA_COLUMN = "fecha";
    private final static String IDP_COLUMN = "id_publicacion";
    private final static String MENSAJE_COLUMN = "mensaje";
    private final static String NOMBRE_COLUMN = "nombre";

    public static void insert(Respuesta respuesta) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " (" + EMAIL_COLUMN + ", " + FECHA_COLUMN + ", " + IDP_COLUMN + ", " + MENSAJE_COLUMN + ", " + NOMBRE_COLUMN + ") VALUES (?,?,?,?,?)";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, respuesta.getEmail());
            statement.setString(2, respuesta.getFecha());
            statement.setInt(3, respuesta.getIdPublicacion());
            statement.setString(4, respuesta.getMensaje());
            statement.setString(5, respuesta.getNombre());
            statement.executeUpdate();
        }
    }

    public static ArrayList<Respuesta> select(String idPublicacion) throws SQLException {
        ArrayList<Respuesta> result = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + IDP_COLUMN + " =?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(idPublicacion));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Respuesta respuesta = new Respuesta(
                            resultSet.getInt("id_respuesta"),
                            resultSet.getString(EMAIL_COLUMN),
                            resultSet.getString(FECHA_COLUMN),
                            resultSet.getInt(IDP_COLUMN),
                            resultSet.getString(MENSAJE_COLUMN),
                            resultSet.getString(NOMBRE_COLUMN)
                    );
                    result.add(respuesta);
                }
            }
        }
        return result;
    }

    public static void delete(int id) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id_respuesta =?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}