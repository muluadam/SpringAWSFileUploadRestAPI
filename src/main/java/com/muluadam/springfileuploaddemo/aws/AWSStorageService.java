package com.muluadam.springfileuploaddemo.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiInternalFrameUI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service

public class AWSStorageService {
    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    public AmazonS3 s3Client;


    public String fileUpload(MultipartFile file)  {
       // File fileObj= new File(file.getOriginalFilename());
        File fileObj = convertMultiPartFileToFile(file);
        String fileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
        fileObj.delete();
        return  "file Uploaded Sucessfully";

    }
    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
           // log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    public  String deleteFile(String fileName){
        s3Client.deleteObject(bucketName,fileName);;
        return  fileName+"  "+ " is removed from  bucket: "+bucketName;
    }

    public byte[] download(String fileName){
        S3Object s3Object= s3Client.getObject(bucketName,fileName);
        S3ObjectInputStream inputStream=s3Object.getObjectContent();
        try{
            byte[] content= IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
