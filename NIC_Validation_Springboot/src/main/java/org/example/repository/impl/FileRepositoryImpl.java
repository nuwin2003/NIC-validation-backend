package org.example.repository.impl;

import org.example.model.File;
import org.example.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepositoryImpl implements FileRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int saveNic(File file) {
        String sql = "INSERT INTO File VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, file.getAge(), file.getBirthday(), file.getGender(), file.getNicNumber());
    }
}
