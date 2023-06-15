package com.javaunit3.springmvc.model;

import javax.persistence.*;

// The VoteEntity class is a JPA entity (Java Persistence API - An entity represents a table stored in a database)
// that represents a vote for a movie.
// It has an id property, a voterName property, and getter and setter methods for each property.
@Entity
@Table(name = "votes")
public class VoteEntity
{
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "voter_Name")
    private String voterName;

    // Define a getter method for the id property.
    public Integer getId() {
        return id;
    }

    // Define a setter method for the id property.
    public void setId(Integer id) {
        this.id = id;
    }

    // Define a getter method for the voterName property.
    public String getVoterName() {
        return voterName;
    }

    // Define a setter method for the voterName property.
    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }
}