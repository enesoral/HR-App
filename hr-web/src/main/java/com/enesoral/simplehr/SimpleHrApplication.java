package com.enesoral.simplehr;

import com.enesoral.simplehr.controllers.ApplicationController;
import com.enesoral.simplehr.services.FileServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class SimpleHrApplication {

    public static void main(String[] args) {
        new File(FileServiceImpl.uploadDirectory).mkdir();
        SpringApplication.run(SimpleHrApplication.class, args);
    }
}
