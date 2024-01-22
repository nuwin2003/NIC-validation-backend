package org.example.service;

import com.opencsv.exceptions.CsvValidationException;
import org.example.model.File;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<String[]> uploadFile(MultipartFile file) throws IOException, CsvValidationException;
    List<String[]> validateFile(List<String[]> records);
    boolean isValidNic(String nic);
    File calculateData(String nic);
    List<File> getAllRecords();

}
