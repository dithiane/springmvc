package com.javaunit3.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
// The BestMovieService class is a Spring component that uses constructor injection to inject a Movie object.
// It has a getBestMovie() method that returns the injected Movie object.
// Define the class as a Spring component.
@Component
public class BestMovieService {

    // Define a private Movie property and use Spring annotations so that it is Autowired.
    public Movie movie;

    // Create a constructor that takes a movie as a parameter.
    // Annotate the method so that Spring will use the constructor to inject a Movie object.
    @Autowired
    public BestMovieService(@Qualifier("batmanMovie") Movie movie) {
        this.movie = movie;
    }

    // Define a method getBestMovie() that returns the movie.
    public Movie getBestMovie() {
        return movie;
    }
}