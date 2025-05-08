# Movie Application Backend

This is a comprehensive Movie Application Backend built using Spring Boot and MongoDB. 
The backend provides user authentication, role-based access control, movie management (CRUD), profile customization, and a review system for movies.
It is secured with JWT authentication, and movie posters are managed using Cloudinary.

## 🚀 Features

* User Registration with OTP Verification
* JWT Authentication with Role-Based Access (ADMIN/USER)
* Movie Management (Add, Update, Delete, Fetch Movies)
* Profile Customization (Profile Picture, Bio, Favorite Genres)
* Movie Ratings and Reviews
* Cloudinary Integration for Image Storage
* Secure API with HTTPS Support

## 📌 Technologies Used

* **Spring Boot** - Backend Framework
* **MongoDB** - NoSQL Database
* **JWT (JSON Web Token)** - Secure Authentication
* **Cloudinary** - Image Storage
* **JavaMailSender** - Email Verification (OTP)
* **Maven** - Dependency Management

## 🗂 Project Structure

```
├── src/main/java/com/movieapp
│   ├── config          # Security and Cloudinary Configurations
│   ├── controllers     # API Controllers
│   ├── models          # Entity Models (User, Movie)
│   ├── repositories    # MongoDB Repositories
│   ├── services        # Service Layer
│   └── utils           # Utility Classes
└── src/main/resources
│   └── application.properties  # Configuration Properties
└── pom.xml             # Maven Dependencies
```

## ⚙️ Setup Instructions

1. Clone the Repository:

   ```bash
   git clone <your-repo-url>
   cd movie-application-backend
   ```

2. Configure Environment Variables:

   * MONGODB\_URI
   * MONGODB\_DATABASE
   * CLOUDINARY\_CLOUD\_NAME
   * CLOUDINARY\_API\_KEY
   * CLOUDINARY\_API\_SECRET
   * MAIL\_USERNAME
   * MAIL\_PASSWORD

3. Build and Run the Application:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## 📌 API Endpoints

### Authentication

* `POST /auth/register` - Register a new user
* `POST /auth/verify` - Verify OTP
* `POST /auth/login` - Login and receive JWT

### Movie Management

* `POST /movies` - Add a new movie (Admin only)
* `PUT /movies/{id}` - Update a movie (Admin only)
* `DELETE /movies/{id}` - Delete a movie (Admin only)
* `GET /movies` - Fetch all movies
* `GET /movies/{id}` - Fetch a single movie

### User Profile

* `GET /user/profile` - Fetch user profile
* `PUT /user/profile` - Update profile

### Ratings and Reviews

* `POST /movies/{id}/review` - Add a review to a movie
* `GET /movies/{id}/reviews` - Fetch all reviews for a movie

## 📌 Security

* JWT Authentication for all protected routes
* Role-Based Access (ADMIN/USER)
* HTTPS Support for Secure API Communication

