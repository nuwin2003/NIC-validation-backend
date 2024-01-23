package org.example.repository;

import org.example.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<File, String> {

    @Query("SELECT COUNT(*) FROM File WHERE gender = 'Male'")
    Long countMales();

    @Query("SELECT COUNT(*) FROM File WHERE gender = 'Female'")
    Long countFemales();

    @Query("SELECT birthday FROM File WHERE gender = 'Male'")
    List<String> findMaleBirthdays();

    @Query("SELECT birthday FROM File WHERE gender = 'Female'")
    List<String> findFemaleBirthdays();
}
