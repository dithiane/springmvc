package com.javaunit3.springmvc;

import com.javaunit3.springmvc.model.MovieEntity;
import com.javaunit3.springmvc.model.VoteEntity;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {
    // Define a bean for the session factory.
    @Bean
    public SessionFactory getFactory() {
        // Create a new session factory using the hibernate configuration file and add the movie and vote entities.
        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(MovieEntity.class)
                .addAnnotatedClass(VoteEntity.class)
                .buildSessionFactory();

        // Return the session factory.
        return factory;
    }
}