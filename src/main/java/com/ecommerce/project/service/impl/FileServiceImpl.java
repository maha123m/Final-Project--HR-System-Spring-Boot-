package com.ecommerce.project.service.impl;

import com.ecommerce.project.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // file name of current  / original file
        String originalFileName = file.getOriginalFilename();

        // generate a unique file name
        String  randomId = UUID.randomUUID().toString();
        // if file name mat.jpg --> and the random id 1234-->new file name 1234.jpg
        String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf(".")));
        String filePath = path + File.separator + fileName;

        //check if path exists ond create
        File folder = new File(path);
        if(!folder.exists())
            folder.mkdir();

        // upload to the server
        Files.copy(file.getInputStream(), Paths.get(filePath));

        //returning file name
        return fileName;
    }

}
