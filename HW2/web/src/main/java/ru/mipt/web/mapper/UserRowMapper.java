package ru.mipt.web.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mipt.web.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        String name = rs.getString("name");
        int age = rs.getInt("age");
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
