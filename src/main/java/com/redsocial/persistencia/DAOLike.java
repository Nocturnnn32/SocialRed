package com.redsocial.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.redsocial.modelo.Like;

public class DAOLike {
    private final static String TABLE_NAME = "likes";
    private final static String EU_COLUMN = "email_usuario";
    private final static String IDP_COLUMN = "id_publicacion";

    public static void insert(Like like) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " (" + EU_COLUMN + ", " + IDP_COLUMN + ") VALUES (?,?)";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, like.getEmailUsuario());
            statement.setInt(2, like.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    public static void delete(Like like) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + EU_COLUMN + " =? AND " + IDP_COLUMN + " =?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, like.getEmailUsuario());
            statement.setInt(2, like.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    public static ArrayList<Like> select(int idPublicacion) throws SQLException {
        ArrayList<Like> result = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + IDP_COLUMN + " =?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPublicacion);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Like like = new Like(resultSet.getString(EU_COLUMN), resultSet.getInt(IDP_COLUMN));
                    result.add(like);
                }
            }
        }
        return result;
    }

    public static Like checkLike(int idPublicacion, String emailUsuario) throws SQLException {
        Like result = null;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + IDP_COLUMN + " =? AND " + EU_COLUMN + " =?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPublicacion);
            statement.setString(2, emailUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = new Like(resultSet.getString(EU_COLUMN), resultSet.getInt(IDP_COLUMN));
                }
            }
        }
        return result;
    }
}