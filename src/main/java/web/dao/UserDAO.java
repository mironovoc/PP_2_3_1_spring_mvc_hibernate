package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import web.models.User;

import java.sql.*;
import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index() {
        return jdbcTemplate.query("SELECT * FROM Users", new BeanPropertyRowMapper<>(User.class));
    }

    public User show(int id) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO Users VALUES(1, ?, ?, ?)", user.getName(), user.getAge(), user.getEmail());
    }

    public void update(int id, User updatedUser) {
        jdbcTemplate.update("UPDATE Users SET name=?, age=?, email=? WHERE id=?", updatedUser.getName(), updatedUser.getAge(), updatedUser.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Users WHERE id=?", id);
    }
}
