package org.example.controller;

import com.opencsv.exceptions.CsvValidationException;
import org.example.model.File;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/csv")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload-file")
    public List<String[]> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, CsvValidationException {
        return fileService.uploadFile(file);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<File>> getAllRecords(){
        List<File> records = fileService.getAllRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }
}
