# 🎬 Movie Booking Application - Backend

This is a Spring Boot-based backend for a **Movie Booking Application**. It allows users to search for movies, book tickets, and manage theaters, screens, and showtimes. It uses an in-memory **H2 database** for development and **Postman** for API testing.

---

## 🚀 Tech Stack

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database**
- **Postman (for API testing)**

---
## ⚙️ Features

- 🎥 **Add/List/Delete Movies**
- 🏛️ **Add Theaters & Screens**
- 🕒 **Schedule Shows**
- 🎟️ **Book/Cancel Tickets**
- 📃 **View Bookings**
- 🔍 **Search Movies by Title, Date, Theater**

- ## 🌐 API Endpoints

> Base URL: `http://localhost:8080/api`

### 🎥 Movies

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/movies` | Get all movies |
| `GET` | `/movies/{id}` | Get movie by ID |
| `POST` | `/movies` | Add a new movie |
| `DELETE` | `/movies/{id}` | Delete a movie |

### 🏛️ Theaters

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/theaters` | List all theaters |
| `POST` | `/theaters` | Add a new theater |

### 🕒 Shows

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/shows` | Get all shows |
| `POST` | `/shows` | Schedule a new show |

### 🎟️ Bookings

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/bookings` | Book a ticket |
| `GET` | `/bookings/{id}` | View a booking by ID |
| `DELETE` | `/bookings/{id}` | Cancel a booking |

