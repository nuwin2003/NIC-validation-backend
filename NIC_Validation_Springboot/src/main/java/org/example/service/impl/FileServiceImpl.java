package org.example.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.model.File;
import org.example.repository.FileRepository;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public List<String[]> uploadFile(MultipartFile file) throws IOException, CsvValidationException {
        Reader reader =new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> records = new ArrayList<>();
        String[] record;
        while ((record = csvReader.readNext()) != null){
            records.add(record);
        }
        return validateFile(records);
    }

    @Override
    public List<String[]> validateFile(List<String[]> records) {
        for (String[] nicArray : records) {
            if(isValidNic(nicArray[0])){
                File file = calculateData(nicArray[0]);
                fileRepository.saveNic(file);
            }
        }
        return records;
    }

    @Override
    public File calculateData(String nic) {
        //Calculations
        String birthYearStr = nic.substring(0, 4);
        int birthdayAndGenderInt =Integer.parseInt(nic.substring(4, 7));
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String gender;

        if(birthdayAndGenderInt > 500){
            gender = "Female";
        }else{
            gender = "Male";
        }
        int birthYear =Integer.parseInt(birthYearStr);
        int ageCalculate = currentYear - birthYear;
        int age = Math.max(ageCalculate, 0);

        LocalDate localDate = LocalDate.of(birthYear, 1, 1);
        LocalDate birthday = localDate.plusDays(birthdayAndGenderInt);

        return new File(nic, birthday.toString(), age, gender);
    }

    @Override
    public boolean isValidNic(String nic) {
        //Validations
        boolean isValid = true;
        if(nic.length() == 12){
            String birthYearStr = nic.substring(0, 4);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            if(Integer.parseInt(birthYearStr) > currentYear){
                isValid = false;
            }
        }else{
            isValid = false;
        }
        return isValid;
    }
}
