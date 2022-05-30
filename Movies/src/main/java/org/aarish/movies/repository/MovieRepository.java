package org.aarish.movies.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.aarish.movies.model.Movie;
import org.aarish.movies.exception.*;

import java.util.List;

public class MovieRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public MovieRepository() {
        this.emf = Persistence.createEntityManagerFactory("movie_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public MovieRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }

    public void addMovie(Movie movie) throws RepoException {
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
    }


    public List<String> getMovies() throws RepoException {
        Query query = entityManager.createQuery("Select m.name from Movie m");
        return query.getResultList();
    }
    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}