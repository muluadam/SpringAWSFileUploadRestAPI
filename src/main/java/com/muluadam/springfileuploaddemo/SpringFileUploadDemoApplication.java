package com.muluadam.springfileuploaddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.muluadam")
public class SpringFileUploadDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFileUploadDemoApplication.class, args);
    }

}
