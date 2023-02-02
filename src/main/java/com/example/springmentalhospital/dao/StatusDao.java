package com.example.springmentalhospital.dao;

import com.example.springmentalhospital.model.Status;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusDao {

    private final JdbcTemplate jdbcTemplate;

    public StatusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Status> indexStatus() {
        return jdbcTemplate.query("SELECT * FROM Status", new BeanPropertyRowMapper<>(Status.class));
    }
}
