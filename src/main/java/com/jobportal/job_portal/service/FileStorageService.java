package com.jobportal.job_portal.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file){
        try{
            Path uploadPath=Paths.get(uploadDir);

            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }


                String originalFilename=file.getOriginalFilename();
                String uniqueFilename=UUID.randomUUID()+ "-"+ originalFilename;

                Path targetPath=uploadPath.resolve(uniqueFilename);

                Files.copy(file.getInputStream(), targetPath,java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                return uniqueFilename;
            }catch(IOException e){
                throw new RuntimeException("failed to store file" +e.getMessage());


            }
        
    }
    
}
