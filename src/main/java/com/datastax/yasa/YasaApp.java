package com.datastax.yasa;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class YasaApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(YasaApp.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
    
}