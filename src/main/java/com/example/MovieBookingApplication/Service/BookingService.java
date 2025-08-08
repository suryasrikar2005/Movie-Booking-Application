package com.example.MovieBookingApplication.Service;

import com.example.MovieBookingApplication.DTO.BookingDTO;
import com.example.MovieBookingApplication.entity.Booking;
import com.example.MovieBookingApplication.entity.BookingStatus;
import com.example.MovieBookingApplication.entity.User;
import com.example.MovieBookingApplication.entity.Show;
import com.example.MovieBookingApplication.Repository.BookingRepository;
import com.example.MovieBookingApplication.Repository.UserRepository;
import com.example.MovieBookingApplication.Repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    // Create a new booking
    public Booking createBooking(BookingDTO bookingDTO) {
        try {
            // Fetch User and Show entities
            User user = userRepository.findById(bookingDTO.getUserid())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + bookingDTO.getUserid()));

            Show show = showRepository.findById(bookingDTO.getShowid())
                    .orElseThrow(() -> new RuntimeException("Show not found with ID: " + bookingDTO.getShowid()));

            // Create new booking entity
            Booking booking = new Booking();

            // Map DTO fields to entity
            booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
            booking.setBookingTime(LocalDateTime.now());
            booking.setPrice(bookingDTO.getPrice());
            booking.setBookingStatus(BookingStatus.PENDING); // Set initial status as PENDING
            booking.setUser(user);
            booking.setShow(show);

            // Save and return the booking
            return bookingRepository.save(booking);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create booking: " + e.getMessage());
        }
    }

    // Get all bookings for a specific user
    public List<Booking> getUserBookings(Long userId) {
        try {
            // Verify user exists
            if (!userRepository.existsById(userId)) {
                throw new RuntimeException("User not found with ID: " + userId);
            }

            List<Booking> bookings = bookingRepository.findByUser_Id(userId);
            if (bookings.isEmpty()) {
                throw new RuntimeException("No bookings found for user ID: " + userId);
            }
            return bookings;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve user bookings: " + e.getMessage());
        }
    }

    // Get all bookings for a specific show
    public List<Booking> getShowBookings(Long showId) {
        try {
            // Verify show exists
            if (!showRepository.existsById(showId)) {
                throw new RuntimeException("Show not found with ID: " + showId);
            }

            List<Booking> bookings = bookingRepository.findByShow_Id(showId);
            if (bookings.isEmpty()) {
                throw new RuntimeException("No bookings found for show ID: " + showId);
            }
            return bookings;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve show bookings: " + e.getMessage());
        }
    }

    // Confirm a booking
    public Booking confirmBooking(Long bookingId) {
        try {
            Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);

            if (!bookingOpt.isPresent()) {
                throw new RuntimeException("Booking not found with ID: " + bookingId);
            }

            Booking booking = bookingOpt.get();

            // Check if booking can be confirmed
            if (booking.getBookingStatus() != BookingStatus.PENDING) {
                throw new RuntimeException("Booking cannot be confirmed. Current status: " + booking.getBookingStatus());
            }

            // Update status to confirmed
            booking.setBookingStatus(BookingStatus.CONFIRMED);

            return bookingRepository.save(booking);

        } catch (Exception e) {
            throw new RuntimeException("Failed to confirm booking: " + e.getMessage());
        }
    }

    // Cancel a booking
    public Booking cancelBooking(Long bookingId) {
        try {
            Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);

            if (!bookingOpt.isPresent()) {
                throw new RuntimeException("Booking not found with ID: " + bookingId);
            }

            Booking booking = bookingOpt.get();

            // Check if booking can be cancelled
            if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
                throw new RuntimeException("Booking is already cancelled");
            }

            // Update status to cancelled
            booking.setBookingStatus(BookingStatus.CANCELLED);

            return bookingRepository.save(booking);

        } catch (Exception e) {
            throw new RuntimeException("Failed to cancel booking: " + e.getMessage());
        }
    }

    // Get bookings by status
    public List<Booking> getBookingsByStatus(BookingStatus status) {
        try {
            List<Booking> bookings = bookingRepository.findByBookingStatus(status);
            if (bookings.isEmpty()) {
                throw new RuntimeException("No bookings found with status: " + status);
            }
            return bookings;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve bookings by status: " + e.getMessage());
        }
    }

    // Additional utility methods

    // Get booking by ID
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Delete booking (if needed)
    public void deleteBooking(Long bookingId) {
        try {
            if (!bookingRepository.existsById(bookingId)) {
                throw new RuntimeException("Booking not found with ID: " + bookingId);
            }
            bookingRepository.deleteById(bookingId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete booking: " + e.getMessage());
        }
    }

    // Update booking status
    public Booking updateBookingStatus(Long bookingId, BookingStatus newStatus) {
        try {
            Booking booking = getBookingById(bookingId);
            booking.setBookingStatus(newStatus);
            return bookingRepository.save(booking);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update booking status: " + e.getMessage());
        }
    }
}