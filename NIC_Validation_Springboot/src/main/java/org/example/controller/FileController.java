package org.example.controller;

import com.opencsv.exceptions.CsvValidationException;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
