package org.example.service;

import com.opencsv.exceptions.CsvValidationException;
import org.example.model.FemaleBirthMonths;
import org.example.model.File;
import org.example.model.MaleBirthMonths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {
    List<String[]> uploadFile(MultipartFile file) throws IOException, CsvValidationException;
    List<String[]> validateFile(List<String[]> records);
    boolean isValidNic(String nic);
    File calculateData(String nic);
    List<File> getAllRecords();
    Optional<File> getSearchDetails(String nicNumber);
    Long getMaleCount();
    Long getFemaleCount();
    MaleBirthMonths getMaleBirthMonths();
    FemaleBirthMonths getFemaleBirthMonths();
}
