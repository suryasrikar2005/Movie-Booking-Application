package com.example.MovieBookingApplication.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieDTO {
    private  Long id;
    private String name;
    private String genre;
    private String description;
    private Integer duration;
    private LocalDate releaseDate;
    private String language;
}
