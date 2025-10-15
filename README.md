Backend Developer Intern Assignment - REST API
This repository contains the backend project for the developer intern assignment. It is a secure, scalable REST API built with Java and Spring Boot, featuring JWT authentication, role-based access control, and full CRUD functionality for a "Tasks" entity.

✅ Core Features Implemented
User Authentication: Secure user registration and login endpoints with password hashing (BCrypt) and JWT (JSON Web Token) generation.

Role-Based Access Control (RBAC): Differentiated access levels between ROLE_USER and ROLE_ADMIN. Users can only manage their own tasks, while an admin could potentially access all tasks.

CRUD APIs for Tasks: Full Create, Read, Update, and Delete operations for tasks, linked to the authenticated user.

API Versioning: All API endpoints are prefixed with /api/v1/.

Input Validation: Incoming data (DTOs) is validated to ensure data integrity.

Centralized Error Handling: Graceful and consistent error responses for invalid requests or server errors.

API Documentation: Integrated Swagger UI for interactive API documentation.

Database Schema: Built using Spring Data JPA, compatible with H2 (for development) and PostgreSQL (for production).

🛠️ Tech Stack
Java 17

Spring Boot 3.2

Spring Security: For authentication and authorization.

Spring Data JPA (Hibernate): For database interaction.

jsonwebtoken (jjwt): For creating and parsing JWTs.

PostgreSQL / H2 Database: A production-grade SQL database and an in-memory DB for easy setup.

Maven: For project and dependency management.

SpringDoc OpenAPI: For automatic Swagger UI generation.

🚀 Getting Started
Prerequisites
JDK 17 or later

Maven 3.6 or later

An IDE (like IntelliJ IDEA or VS Code)

Running the Application
Clone the repository:

git clone <your-repo-url>
cd backend

Run the application using Maven:

mvn spring-boot:run

The application will start on http://localhost:8080.

Database Configuration
By default, the application uses an in-memory H2 database. The H2 console is accessible at http://localhost:8080/h2-console with the credentials in application.properties.

To switch to PostgreSQL, uncomment the PostgreSQL properties in src/main/resources/application.properties and provide your database URL, username, and password.

📄 API Documentation & Endpoints
Once the application is running, you can access the interactive Swagger UI documentation at:

http://localhost:8080/swagger-ui.html

Key Endpoints
Authentication (/api/v1/auth)
POST /register: Register a new user.

POST /login: Authenticate a user and receive a JWT.

Tasks (/api/v1/tasks)
All task endpoints require a valid JWT in the Authorization: Bearer <token> header.

POST /: Create a new task (for the logged-in user).

GET /: Get all tasks belonging to the logged-in user.

GET /{id}: Get a specific task by its ID.

PUT /{id}: Update a task.

DELETE /{id}: Delete a task.

GET /all: Get all tasks from all users (Requires ROLE_ADMIN).

💡 Scalability Note
This application is built on a monolithic architecture, which is suitable for many projects. For massive scale, several strategies could be employed:

Load Balancing: Deploy multiple instances of this application and use a load balancer (like Nginx or an AWS ALB) to distribute incoming traffic. This provides both scalability and high availability.

Caching: Integrate a distributed cache like Redis. We could cache frequently accessed data, such as user details or popular tasks, to reduce database load and improve response times.

Database Scaling:

Read Replicas: For read-heavy workloads, we can configure read replicas in our database. The application would direct all write operations to the primary database and read operations to the replicas.

Sharding: For write-heavy workloads, we could partition the database (e.g., sharding by userId) to distribute the data across multiple database servers.

Microservices Architecture: For ultimate scalability and separation of concerns, this monolith could be broken down into smaller, independent microservices. For example:

Auth Service: A dedicated service for user registration, login, and JWT management.

Task Service: A service responsible only for CRUD operations on tasks.

This allows each service to be scaled independently.
