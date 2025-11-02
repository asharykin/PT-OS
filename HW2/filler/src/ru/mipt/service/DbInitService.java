package ru.mipt.service;

import ru.mipt.model.User;

import java.sql.*;
import java.util.List;

public class DbInitService implements AutoCloseable {

    private final Connection conn;

    public DbInitService(String url, String user, String password) throws SQLException {
        this.conn = DriverManager.getConnection(url, user, password);
    }

    public void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS data (name VARCHAR(255) NOT NULL, age INT NOT NULL)";
        try (Statement stat = conn.createStatement()) {
            stat.execute(sql);
        }
    }

    public void insertUsersIntoTable(List<User> users) throws SQLException {
        String sql = "INSERT INTO data (name, age) VALUES (?, ?)";
        try (PreparedStatement stat = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (User user : users) {
                stat.setString(1, user.getName());
                stat.setInt(2, user.getAge());
                stat.addBatch();
            }
            stat.executeBatch();
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }
}
