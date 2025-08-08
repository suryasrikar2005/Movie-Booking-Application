package com.example.MovieBookingApplication.DTO;

import com.example.MovieBookingApplication.entity.UserRole;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private UserRole role;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor for registration (without sensitive data)
    public UserDTO() {}

    public UserDTO(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = UserRole.CUSTOMER;
        this.isActive = true;
    }

    // Method to get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}

// Separate DTO for user registration (without ID and timestamps)
@Data
class UserRegistrationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
}

// Separate DTO for user login
@Data
class UserLoginDTO {
    private String email;
    private String password;
}

// Separate DTO for user profile update
@Data
class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String currentPassword;
    private String newPassword;
}

