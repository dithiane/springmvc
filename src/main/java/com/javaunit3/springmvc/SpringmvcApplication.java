package com.javaunit3.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Annotate it with SpringBootApplication
@SpringBootApplication
public class SpringmvcApplication {
    // Inside the public class SpringmvcApplication create a main method
    public static void main(String[] args) {
        // Inside the main method write out SpringApplication.run(SpringmvcApplication.class, args)
        SpringApplication.run(SpringmvcApplication.class, args);
    }
}
