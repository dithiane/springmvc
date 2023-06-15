package com.javaunit3.springmvc;
// This code is a Spring Boot web application that allows users to vote for their
// favorite movie and displays the best movie based on the number of votes.

// Define the Movie interface.
public interface Movie {

    // Define the getTitle() method to return the title of the movie.
    public String getTitle();

    // Define the getMaturityRating() method to return the maturity rating of the movie.
    public String getMaturityRating();

    // Define the getGenre() method to return the genre of the movie.
    public String getGenre();
}
