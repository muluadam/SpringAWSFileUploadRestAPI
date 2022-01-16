package com.muluadam.springfileuploaddemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/fileUpload")
    public ResponseEntity<Object> upload(@RequestParam("File") MultipartFile file){

        try {
            File newFile= new File("solilove.docx");
            FileOutputStream fos= new FileOutputStream(newFile);
            fos.write(file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
return new  ResponseEntity<Object>("File Uploaded Succesffully",HttpStatus.OK);

    }
}
