package com.example.MovieBookingApplication.Repository;

import com.example.MovieBookingApplication.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Optional<List<Movie>> findByGenre(String genre);
    Optional<List<Movie>> findByLanguage(String language);

    Optional<List<Movie>> findByName(String name);

}
