package com.example.MovieBookingApplication.Repository;


import com.example.MovieBookingApplication.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    Optional<List< Theatre>>findByLocation(String location);
}

