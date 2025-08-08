
package com.example.MovieBookingApplication.Repository;

import com.example.MovieBookingApplication.entity.User;
import com.example.MovieBookingApplication.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if email exists
    boolean existsByEmail(String email);

    // Find users by role
    List<User> findByRole(UserRole role);

    // Find active users
    List<User> findByIsActiveTrue();

    // Find inactive users
    List<User> findByIsActiveFalse();

    // Find users by role and active status
    List<User> findByRoleAndIsActive(UserRole role, Boolean isActive);

    // Find users by name (case-insensitive)
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    // Find users created after a certain date
    List<User> findByCreatedAtAfter(LocalDateTime date);

    // Find users by phone number
    Optional<User> findByPhoneNumber(String phoneNumber);

    // Custom query to find users with bookings count
    @Query("SELECT u FROM User u LEFT JOIN u.bookings b GROUP BY u.id ORDER BY COUNT(b) DESC")
    List<User> findUsersOrderedByBookingCount();

    // Custom query to find active customers
    @Query("SELECT u FROM User u WHERE u.role = :role AND u.isActive = true")
    List<User> findActiveCustomers(@Param("role") UserRole role);

    // Count users by role
    Long countByRole(UserRole role);

    // Count active users
    Long countByIsActiveTrue();
}
