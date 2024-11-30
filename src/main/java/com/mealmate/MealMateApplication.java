package com.mealmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MealMateApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MealMateApplication.class);
        app.run(args);
    }
}