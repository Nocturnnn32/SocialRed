package com.redsocial.persistencia;

import com.redsocial.auxiliares.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.redsocial.modelo.Usuario;

public class DAOUsuario {
    private final static String TABLE_NAME = "usuarios";
    private final static String NAME_COLUMN = "nombre";
    private final static String EMAIL_COLUMN = "email";
    private final static String PWD_COLUMN = "pwd";

  public static Usuario login(String email, String pwd) throws SQLException {
    Usuario result = null;
    String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_COLUMN + " =? AND " + PWD_COLUMN + " =?";
    try (Connection connection = PostgresConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        // Encriptar la contraseña antes de la comparación
        String encryptedPwd = Utilidades.Encriptar(pwd);
        statement.setString(1, email);
        statement.setString(2, encryptedPwd);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                result = new Usuario(resultSet.getString(NAME_COLUMN), resultSet.getString(EMAIL_COLUMN), resultSet.getString(PWD_COLUMN));
            }
        }
    }
    return result;
}



    public static Usuario select(Usuario usuario) throws SQLException {
        Usuario result = null;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_COLUMN + " =?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getEmail());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = new Usuario(resultSet.getString(NAME_COLUMN), resultSet.getString(EMAIL_COLUMN), resultSet.getString(PWD_COLUMN));
                }
            }
        }
        return result;
    }

    public static Usuario selectWithID(int idUsuario) throws SQLException {
        Usuario result = null;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id_usuario =?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = new Usuario(resultSet.getString(NAME_COLUMN), resultSet.getString(EMAIL_COLUMN), resultSet.getString(PWD_COLUMN));
                }
            }
        }
        return result;
    }

    public static void delete(int id) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id_usuario =?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public static ArrayList<Usuario> selectAll() throws SQLException {
        ArrayList<Usuario> result = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Usuario usuario = new Usuario(resultSet.getString(NAME_COLUMN), resultSet.getString(EMAIL_COLUMN), resultSet.getString(PWD_COLUMN));
                    result.add(usuario);
                }
            }
        }
        return result;
    }

    public static Usuario insert(Usuario usuario) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " (" + NAME_COLUMN + ", " + EMAIL_COLUMN + ", " + PWD_COLUMN + ") VALUES (?,?,?)";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getPwd());
            statement.executeUpdate();
        }
        return usuario;
    }

    public static void update(Usuario usuario) throws SQLException {
        String query = "UPDATE " + TABLE_NAME + " SET " + NAME_COLUMN + " = ?, " + EMAIL_COLUMN + " = ?, " + PWD_COLUMN + " = ? WHERE id_usuario = ?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getPwd());
            statement.setInt(4, usuario.getIdUsuario());
            statement.executeUpdate();
        }
    }
}