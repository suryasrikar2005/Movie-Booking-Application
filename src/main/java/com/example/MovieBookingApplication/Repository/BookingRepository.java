package com.example.MovieBookingApplication.Repository;

import com.example.MovieBookingApplication.entity.Booking;
import com.example.MovieBookingApplication.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find bookings by user ID
    List<Booking> findByUser_Id(Long userId);

    // Find bookings by show ID
    List<Booking> findByShow_Id(Long showId);

    // Find bookings by booking status
    List<Booking> findByBookingStatus(BookingStatus bookingStatus);

    // Additional useful methods you might need:

    // Find bookings by user email (if User entity has email field)
    // List<Booking> findByUser_Email(String email);

    // Find bookings by show and status
    List<Booking> findByShow_IdAndBookingStatus(Long showId, BookingStatus bookingStatus);

    // Find bookings by user and status
    List<Booking> findByUser_IdAndBookingStatus(Long userId, BookingStatus bookingStatus);

    // Count bookings by show
    Long countByShow_Id(Long showId);

    // Count bookings by user
    Long countByUser_Id(Long userId);

    // Find bookings by status ordered by booking time
    List<Booking> findByBookingStatusOrderByBookingTimeDesc(BookingStatus bookingStatus);
}