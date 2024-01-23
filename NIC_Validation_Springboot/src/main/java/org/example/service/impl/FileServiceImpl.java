package org.example.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.model.FemaleBirthMonths;
import org.example.model.File;
import org.example.model.MaleBirthMonths;
import org.example.repository.FileRepository;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.*;

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
                fileRepository.save(file);
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
        LocalDate birthday = (gender.equals("Male")) ? localDate.plusDays(birthdayAndGenderInt) : localDate.plusDays(birthdayAndGenderInt-500);

        return new File(nic, birthday.toString(), age, gender);
    }

    @Override
    public List<File> getAllRecords() {
        return fileRepository.findAll();
    }

    @Override
    public Optional<File> getSearchDetails(String nicNumber) {
        return fileRepository.findById(nicNumber);
    }

    @Override
    public Long getMaleCount() {
        return fileRepository.countMales();
    }

    @Override
    public Long getFemaleCount() {
        return fileRepository.countFemales();
    }

    @Override
    public MaleBirthMonths getMaleBirthMonths() {
        List<String> birthdayList = fileRepository.findMaleBirthdays();
        MaleBirthMonths maleBirthMonths = new MaleBirthMonths();
        for (String birthday: birthdayList
             ) {
            String birthMonth = birthday.substring(5,7);

            switch (Integer.parseInt(birthMonth)){
                case 1:
                    maleBirthMonths.setJanuary(maleBirthMonths.getJanuary() + 1);
                    break;
                case 2:
                    maleBirthMonths.setFebruary(maleBirthMonths.getFebruary() + 1);
                    break;
                case 3:
                    maleBirthMonths.setMarch(maleBirthMonths.getMarch() + 1);
                    break;
                case 4:
                    maleBirthMonths.setApril(maleBirthMonths.getApril() + 1);
                    break;
                case 5:
                    maleBirthMonths.setMay(maleBirthMonths.getMay() + 1);
                    break;
                case 6:
                    maleBirthMonths.setJune(maleBirthMonths.getJune() + 1);
                    break;
                case 7:
                    maleBirthMonths.setJuly(maleBirthMonths.getJuly() + 1);
                    break;
                case 8:
                    maleBirthMonths.setAugust(maleBirthMonths.getAugust() + 1);
                    break;
                case 9:
                    maleBirthMonths.setSeptember(maleBirthMonths.getSeptember() + 1);
                    break;
                case 10:
                    maleBirthMonths.setOctober(maleBirthMonths.getOctober() + 1);
                    break;
                case 11:
                    maleBirthMonths.setNovember(maleBirthMonths.getNovember() + 1);
                    break;
                case 12:
                    maleBirthMonths.setDecember(maleBirthMonths.getDecember() + 1);
                    break;
            }
        }
        return maleBirthMonths;
    }

    @Override
    public FemaleBirthMonths getFemaleBirthMonths() {
        List<String> birthdayList = fileRepository.findFemaleBirthdays();
        FemaleBirthMonths femaleBirthMonths = new FemaleBirthMonths();
        for (String birthday: birthdayList
        ) {
            String birthMonth = birthday.substring(5,7);

            switch (Integer.parseInt(birthMonth)){
                case 1:
                    femaleBirthMonths.setJanuary(femaleBirthMonths.getJanuary() + 1);
                    break;
                case 2:
                    femaleBirthMonths.setFebruary(femaleBirthMonths.getFebruary() + 1);
                    break;
                case 3:
                    femaleBirthMonths.setMarch(femaleBirthMonths.getMarch() + 1);
                    break;
                case 4:
                    femaleBirthMonths.setApril(femaleBirthMonths.getApril() + 1);
                    break;
                case 5:
                    femaleBirthMonths.setMay(femaleBirthMonths.getMay() + 1);
                    break;
                case 6:
                    femaleBirthMonths.setJune(femaleBirthMonths.getJune() + 1);
                    break;
                case 7:
                    femaleBirthMonths.setJuly(femaleBirthMonths.getJuly() + 1);
                    break;
                case 8:
                    femaleBirthMonths.setAugust(femaleBirthMonths.getAugust() + 1);
                    break;
                case 9:
                    femaleBirthMonths.setSeptember(femaleBirthMonths.getSeptember() + 1);
                    break;
                case 10:
                    femaleBirthMonths.setOctober(femaleBirthMonths.getOctober() + 1);
                    break;
                case 11:
                    femaleBirthMonths.setNovember(femaleBirthMonths.getNovember() + 1);
                    break;
                case 12:
                    femaleBirthMonths.setDecember(femaleBirthMonths.getDecember() + 1);
                    break;
            }
        }
        return femaleBirthMonths;
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
