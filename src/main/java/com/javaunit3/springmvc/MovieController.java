package com.javaunit3.springmvc;

import com.javaunit3.springmvc.model.MovieEntity;
import com.javaunit3.springmvc.model.VoteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class MovieController
{
    // Autowire the BestMovieService and SessionFactory dependencies.
    @Autowired
    private BestMovieService bestMovieService;

    @Autowired
    // The SessionFactory object is used to create and manage Hibernate Session objects
    private SessionFactory sessionFactory;

    // Define a request mapping for the index page.
    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }

    // Define a request mapping for the best movie page.
    @RequestMapping("/bestMovie")
    // The getBestMoviePage() method handles requests to the best movie page ("/bestMovie") and retrieves the movie
    // with the most votes from the database.
    // It then adds the movie and the names of the voters who voted for it to the model and returns
    // the name of the Thymeleaf template to be rendered.
    public String getBestMoviePage(Model model) {
        // Get the current session.
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        // Get a list of all movie entities and sort them by the number of votes they have.
        List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();
        movieEntityList.sort(Comparator.comparing(movieEntity -> movieEntity.getVotes().size()));

        // Get the movie entity with the most votes and create a list of the names of the voters who voted for it.
        MovieEntity movieWithMostVotes = movieEntityList.get(movieEntityList.size() - 1);
        List<String> voterNames = new ArrayList<>();

        for (VoteEntity vote: movieWithMostVotes.getVotes()) {
            voterNames.add(vote.getVoterName());
        }

        // Join the list of voter names into a comma-separated string.
        String voterNamesList = String.join(",", voterNames);

        // Add the best movie and its voters to the model.
        model.addAttribute("bestMovie", movieWithMostVotes.getTitle());
        model.addAttribute("bestMovieVoters", voterNamesList);

        // Commit the transaction and return the best movie page.
        session.getTransaction().commit();

        return "bestMovie";
    }

    // Define a request mapping for the vote for best movie form page.
    @RequestMapping("/voteForBestMovieForm")
    // The voteForBestMovieFormPage() method handles requests to the vote for best movie form page
    // ("/voteForBestMovieForm") and retrieves a list of all movies from the database.
    // It then adds the list of movies to the model and returns the name of the Thymeleaf template to be rendered.
    public String voteForBestMovieFormPage(Model model) {
        // Get the current session.
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        // Get a list of all movie entities and add them to the model.
        List<MovieEntity> movieEntityList = session.createQuery("from MovieEntity").list();

        session.getTransaction().commit();

        model.addAttribute("movies", movieEntityList);

        return "voteForBestMovie";
    }

    // Define a request mapping for voting for the best movie.
    @RequestMapping("/voteForBestMovie")
    // The voteForBestMovie() method handles form submissions to the vote for best movie form ("/voteForBestMovie").
    // It retrieves the movie ID and voter name from the request, adds a new vote for the specified movie and voter
    // to the database, and returns the name of the Thymeleaf template to be rendered.
    public String voteForBestMovie(HttpServletRequest request, Model model) {
        // Get the movie id and voter name from the request.
        String movieId = request.getParameter("movieId");
        String voterName = request.getParameter("voterName");

        // Get the current session.
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        // Get the movie entity with the specified id and add a new vote with the specified voter name.
        MovieEntity movieEntity = (MovieEntity) session.get(MovieEntity.class, Integer.parseInt(movieId));
        VoteEntity newVote = new VoteEntity();
        newVote.setVoterName(voterName);
        movieEntity.addVote(newVote);

        // Update the movie entity and commit the transaction.
        session.update(movieEntity);

        session.getTransaction().commit();

        // Return the vote for best movie page.
        return "voteForBestMovie";
    }

    // Define a request mapping for the added movie form page.
    @RequestMapping("/addMovieForm")
    // The addMovie() method handles form submissions to the added movie form ("/addMovie").
    // It retrieves the movie title, maturity rating, and genre from the request,
    // creates a new MovieEntity object with the specified properties, and adds it to the database.
    // It then returns the name of the Thymeleaf template to be rendered.
    public String addMovieForm() {
        return "addMovie";
    }

    // Define a request mapping for adding a movie.
    @RequestMapping("/addMovie")
    public String addMovie(HttpServletRequest request)
    {
        // Get the movie title, maturity rating, and genre from the request.
        String movieTitle = request.getParameter("movieTitle");
        String maturityRating = request.getParameter("maturityRating");
        String genre = request.getParameter("genre");

        // Create a new movie entity with the specified title, maturity rating, and genre.
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(movieTitle);
        movieEntity.setMaturityRating(maturityRating);
        movieEntity.setGenre(genre);

        // Get the current session.
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        // Save the new movie entity and commit the transaction.
        session.save(movieEntity);

        session.getTransaction().commit();

        // Return the add movie page.
        return "addMovie";
    }
}