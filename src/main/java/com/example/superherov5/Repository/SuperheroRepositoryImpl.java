package com.example.superherov5.Repository;


import com.example.superherov5.DTO.CitiesDTO;
import com.example.superherov5.DTO.SuperpowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SuperheroRepositoryImpl extends SuperheroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CitiesDTO> getAllCities() {
        String sql = "SELECT * FROM city";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CitiesDTO.class));
    }

    @Override
    public List<SuperpowerDTO> getAllSuperpowers() {
        String sql = "SELECT * FROM superpower";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SuperpowerDTO.class));
    }


}
