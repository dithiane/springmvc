package com.javaunit3.springmvc;

import org.springframework.stereotype.Component;

// Define the class as a Spring component.
@Component
public class TitanicMovie implements Movie {

    // Implement the getTitle() method to return the title of the movie.
    public String getTitle() {
        return "Titanic";
    }

    // Implement the getMaturityRating() method to return the maturity rating of the movie.
    public String getMaturityRating() {
        return "PG-13";
    }

    // Implement the getGenre() method to return the genre of the movie.
    public String getGenre() {
        return "Romance";
    }
}