package com.alliander.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Application {

    public static final String API_PREFIX = "/api/v1.0/";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

