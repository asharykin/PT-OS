package ru.mipt.filler.mapper;

import ru.mipt.filler.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public User mapLineToUser(String line) {
        String[] data = line.split(",");
        String name = data[0].trim();
        int age = Integer.parseInt(data[1].trim());
        return new User(name, age);
    }

    public User mapRowToUser(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        int age = rs.getInt("age");
        return new User(name, age);
    }

    public void prepareStatementWithUser(PreparedStatement ps, User user) throws SQLException {
        ps.setString(1, user.getName());
        ps.setInt(2, user.getAge());
    }
}
