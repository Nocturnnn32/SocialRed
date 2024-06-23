package com.redsocial.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.redsocial.modelo.MensajesPrivados;

public class DAOMensajesPrivados {
    private final static String TABLE_NAME = "mensajes_privados";
    private final static String ID_COLUMN = "id_mensaje";
    private final static String FECHA_COLUMN = "fecha";
    private final static String EMAIL_DESTINATARIO_COLUMN = "email_destinatario";
    private final static String EMAIL_EMISOR_COLUMN = "email_emisor";
    private final static String MENSAJE_COLUMN = "mensaje";

    public static void insert(MensajesPrivados msg) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " (" + FECHA_COLUMN + ", " + EMAIL_DESTINATARIO_COLUMN + ", " + EMAIL_EMISOR_COLUMN + ", " + MENSAJE_COLUMN + ") VALUES (?,?,?,?)";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, msg.getFecha());
            statement.setString(2, msg.getDestinatario());
            statement.setString(3, msg.getEmisor());
            statement.setString(4, msg.getMensaje());
            statement.executeUpdate();
        }
    }

    public static void update(MensajesPrivados msg) throws SQLException {
        String query = "UPDATE " + TABLE_NAME + " SET " + MENSAJE_COLUMN + " = ?, " + FECHA_COLUMN + " = ? WHERE " + ID_COLUMN + " = ?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, msg.getMensaje());
            statement.setString(2, msg.getFecha());
            statement.setInt(3, msg.getIdmensaje());
            statement.executeUpdate();
        }
    }

    public static MensajesPrivados select(String idmensaje) throws SQLException {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = ?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(idmensaje));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new MensajesPrivados(
                            resultSet.getInt(ID_COLUMN),
                            resultSet.getString(FECHA_COLUMN),
                            resultSet.getString(EMAIL_DESTINATARIO_COLUMN),
                            resultSet.getString(EMAIL_EMISOR_COLUMN),
                            resultSet.getString(MENSAJE_COLUMN)
                    );
                }
            }
        }
        return null;
    }

    public static ArrayList<MensajesPrivados> selectAll() throws SQLException {
        ArrayList<MensajesPrivados> result = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                result.add(new MensajesPrivados(
                        resultSet.getInt(ID_COLUMN),
                        resultSet.getString(FECHA_COLUMN),
                        resultSet.getString(EMAIL_DESTINATARIO_COLUMN),
                        resultSet.getString(EMAIL_EMISOR_COLUMN),
                        resultSet.getString(MENSAJE_COLUMN)
                ));
            }
        }
        return result;
    }

    public static ArrayList<MensajesPrivados> selectMsgUser(String emailUser) throws SQLException {
        ArrayList<MensajesPrivados> result = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_DESTINATARIO_COLUMN + " = ?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, emailUser);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new MensajesPrivados(
                            resultSet.getInt(ID_COLUMN),
                            resultSet.getString(FECHA_COLUMN),
                            resultSet.getString(EMAIL_DESTINATARIO_COLUMN),
                            resultSet.getString(EMAIL_EMISOR_COLUMN),
                            resultSet.getString(MENSAJE_COLUMN)
                    ));
                }
            }
        }
        return result;
    }

    public static void delete(String id) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = ?";
        try (Connection connection = PostgresConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(id));
            statement.executeUpdate();
        }
    }
}