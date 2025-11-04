package ru.mipt.filler.repository;

import ru.mipt.filler.mapper.UserMapper;
import ru.mipt.filler.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements AutoCloseable {

    private final Connection conn;
    private final UserMapper userMapper;

    public UserRepository(String url, String user, String password, UserMapper userMapper) throws SQLException {
        this.conn = DriverManager.getConnection(url, user, password);
        this.userMapper = userMapper;
    }

    public void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS data (name VARCHAR(255) NOT NULL, age INT NOT NULL)";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    public void insertUsersIntoTable(List<User> users) throws SQLException {
        String sql = "INSERT INTO data (name, age) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (User user : users) {
                userMapper.prepareStatementWithUser(ps, user);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public List<User> selectAllUsersFromTable() throws SQLException {
        String sql = "SELECT name, age FROM data";
        List<User> users = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                users.add(userMapper.mapRowToUser(rs));
            }
        }
        return users;
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }
}
