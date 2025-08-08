
package com.example.MovieBookingApplication.Service;

import com.example.MovieBookingApplication.DTO.UserDTO;
import com.example.MovieBookingApplication.entity.User;
import com.example.MovieBookingApplication.entity.UserRole;
import com.example.MovieBookingApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user (Registration)
    public User createUser(UserDTO userDTO) {
        try {
            // Check if email already exists
            if (userRepository.existsByEmail(userDTO.getEmail())) {
                throw new RuntimeException("Email already exists: " + userDTO.getEmail());
            }

            // Create new user entity
            User user = new User();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword()); // Note: In real app, hash this password
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setRole(userDTO.getRole() != null ? userDTO.getRole() : UserRole.CUSTOMER);
            user.setIsActive(true);

            return userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create user: " + e.getMessage());
        }
    }

    // Get user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    // Get user by email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get users by role
    public List<User> getUsersByRole(UserRole role) {
        List<User> users = userRepository.findByRole(role);
        if (users.isEmpty()) {
            throw new RuntimeException("No users found with role: " + role);
        }
        return users;
    }

    // Get active users only
    public List<User> getActiveUsers() {
        return userRepository.findByIsActiveTrue();
    }

    // Get inactive users only
    public List<User> getInactiveUsers() {
        return userRepository.findByIsActiveFalse();
    }

    // Update user
    public User updateUser(Long userId, UserDTO userDTO) {
        try {
            User existingUser = getUserById(userId);

            // Check if new email already exists (if email is being changed)
            if (!existingUser.getEmail().equals(userDTO.getEmail()) &&
                    userRepository.existsByEmail(userDTO.getEmail())) {
                throw new RuntimeException("Email already exists: " + userDTO.getEmail());
            }

            // Update user fields
            existingUser.setFirstName(userDTO.getFirstName());
            existingUser.setLastName(userDTO.getLastName());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setPhoneNumber(userDTO.getPhoneNumber());

            if (userDTO.getRole() != null) {
                existingUser.setRole(userDTO.getRole());
            }

            if (userDTO.getIsActive() != null) {
                existingUser.setIsActive(userDTO.getIsActive());
            }

            return userRepository.save(existingUser);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage());
        }
    }

    // Delete user (soft delete - deactivate)
    public User deactivateUser(Long userId) {
        try {
            User user = getUserById(userId);
            user.setIsActive(false);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deactivate user: " + e.getMessage());
        }
    }

    // Activate user
    public User activateUser(Long userId) {
        try {
            User user = getUserById(userId);
            user.setIsActive(true);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to activate user: " + e.getMessage());
        }
    }

    // Delete user permanently
    public void deleteUser(Long userId) {
        try {
            if (!userRepository.existsById(userId)) {
                throw new RuntimeException("User not found with ID: " + userId);
            }
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user: " + e.getMessage());
        }
    }

    // Login validation
    public User validateLogin(String email, String password) {
        try {
            User user = getUserByEmail(email);

            if (!user.getIsActive()) {
                throw new RuntimeException("Account is deactivated. Please contact support.");
            }

            // Note: In real application, compare hashed passwords
            if (!user.getPassword().equals(password)) {
                throw new RuntimeException("Invalid email or password");
            }

            return user;

        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

    // Search users by name
    public List<User> searchUsersByName(String searchTerm) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                searchTerm, searchTerm);
    }

    // Get user statistics
    public Long getTotalUsersCount() {
        return userRepository.count();
    }

    public Long getActiveUsersCount() {
        return userRepository.countByIsActiveTrue();
    }

    public Long getCustomersCount() {
        return userRepository.countByRole(UserRole.CUSTOMER);
    }

    // Change user role
    public User changeUserRole(Long userId, UserRole newRole) {
        try {
            User user = getUserById(userId);
            user.setRole(newRole);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to change user role: " + e.getMessage());
        }
    }

    // Update password
    public User updatePassword(Long userId, String currentPassword, String newPassword) {
        try {
            User user = getUserById(userId);

            // Verify current password
            if (!user.getPassword().equals(currentPassword)) {
                throw new RuntimeException("Current password is incorrect");
            }

            // Update password (In real app, hash the password)
            user.setPassword(newPassword);
            return userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update password: " + e.getMessage());
        }
    }
}
