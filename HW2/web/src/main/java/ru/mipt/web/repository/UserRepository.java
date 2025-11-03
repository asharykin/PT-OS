package ru.mipt.web.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mipt.web.mapper.UserRowMapper;
import ru.mipt.web.model.User;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    public List<User> findAll() {
        String sql = "SELECT name, age FROM data";
        List<User> users = jdbcTemplate.query(sql, userRowMapper);
        return users;
    }
}
