package org.example.controller;

import com.opencsv.exceptions.CsvValidationException;
import org.example.model.FemaleBirthMonths;
import org.example.model.File;
import org.example.model.MaleBirthMonths;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/csv")
public class FileController {

    @Autowired
    FileService fileService;

    // Upload File
    @PostMapping("/upload-file")
    public List<String[]> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, CsvValidationException {
        return fileService.uploadFile(file);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<File>> getAllRecords(){
        List<File> records = fileService.getAllRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @GetMapping("/{nicNumber}")
    public ResponseEntity<Optional<File>> getSearchDetails(@PathVariable String nicNumber){
        Optional<File> record = fileService.getSearchDetails(nicNumber);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @GetMapping("/male-count")
    public Long getMaleCount(){
        return fileService.getMaleCount();
    }

    @GetMapping("/female-count")
    public Long getFemaleCount(){
        return fileService.getFemaleCount();
    }

    @GetMapping("/male-birth-months")
    public MaleBirthMonths getMaleBirthMonths(){
        return fileService.getMaleBirthMonths();
    }

    @GetMapping("/female-birth-months")
    public FemaleBirthMonths getFemaleBirthMonths(){
        return fileService.getFemaleBirthMonths();
    }

    @GetMapping("/record-count")
    public Long getRecordCount(){
        return fileService.getRecordCount();
    }
}
