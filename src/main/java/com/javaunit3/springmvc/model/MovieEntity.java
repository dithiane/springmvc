package com.javaunit3.springmvc.model;

import javax.persistence.*;
import java.util.List;

// The MovieEntity class is a JPA entity ((Java Persistence API - An entity represents a table stored in a database)
// that represents a movie.
// It has an id property, a title property, a maturityRating property, a genre property,
// and a list of VoteEntity objects. It also has getter and setter methods for each property,
// as well as methods to add a vote to the list of votes.
@Entity
@Table(name = "movies")
public class MovieEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_Id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "maturity_Rating")
    private String maturityRating;

    @Column(name = "genre")
    private String genre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<VoteEntity> votes;

    // Define a getter method for the votes' property.
    public List<VoteEntity> getVotes() {
        return votes;
    }

    // Define a setter method for the votes' property.
    public void setVotes(List<VoteEntity> votes) {
        this.votes = votes;
    }

    // Define a method to add a vote to the vote list.
    public void addVote(VoteEntity vote) {
        this.votes.add(vote);
    }

    // Define a getter method for the id property.
    public Integer getId() {
        return id;
    }

    // Define a setter method for the id property.
    public void setId(Integer id) {
        this.id = id;
    }

    // Define a getter method for the title property.
    public String getTitle()
    {
        return title;
    }

    // Define a setter method for the title property.
    public void setTitle(String title) {
        this.title = title;
    }

    // Define a getter method for the maturityRating property.
    public String getMaturityRating() {
        return maturityRating;
    }

    // Define a setter method for the maturityRating property.
    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    // Define a getter method for the genre property.
    public String getGenre() {
        return genre;
    }

    // Define a setter method for the genre property.
    public void setGenre(String genre) {
        this.genre = genre;
    }
}