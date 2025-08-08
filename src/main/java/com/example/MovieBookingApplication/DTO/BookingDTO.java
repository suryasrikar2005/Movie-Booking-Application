package com.example.MovieBookingApplication.DTO;

import com.example.MovieBookingApplication.entity.BookingStatus;

import java.time.LocalDateTime;

public class BookingDTO {
    private Long id;
    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
    private Double price;
    private BookingStatus bookingStatus;
    private Long userid;
    private Long showid;
}
