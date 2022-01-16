package com.muluadam.springfileuploaddemo.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/aws")
public class AWSFileStorageController {


    @Autowired
    public AWSStorageService service;


    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file){
        return new  ResponseEntity<Object>(service.fileUpload(file),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<Object> delete(@PathVariable("fileName") String fileName){

        return  new ResponseEntity<>(service.deleteFile(fileName),HttpStatus.GONE);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable("fileName") String fileName){
        byte[] data= service.download(fileName);
        ByteArrayResource byteArrayResource= new ByteArrayResource(data);
        return  ResponseEntity.ok()
                .contentLength(data.length)
                .header("Content-type","application/octate-stream")
                .header("Content-disposition","attachement; filename=\""+fileName+ "\"")
                .body(byteArrayResource);
    }

}
