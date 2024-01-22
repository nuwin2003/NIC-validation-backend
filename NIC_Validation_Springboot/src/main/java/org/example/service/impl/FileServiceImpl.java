package org.example.service.impl;

import org.example.repository.FileRepository;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;
}
