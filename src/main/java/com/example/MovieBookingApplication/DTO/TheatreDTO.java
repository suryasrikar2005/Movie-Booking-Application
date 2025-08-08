package com.example.MovieBookingApplication.DTO;

import lombok.Data;

@Data
public class TheatreDTO {
    private String theatreName;
    private String theatreLocation;
    private Integer theatreCapacity; // changed to int
    private String theatreScreenType; // fixed typo
}

