package com.example.MovieBookingApplication.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowDTO {
    private LocalDateTime showTime;
    private Double price;
    private Long movieId;
    private Long theatreId;
}
