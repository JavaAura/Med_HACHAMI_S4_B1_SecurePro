# SecurePro API

A REST API for managing products, categories, and user roles in a secure and scalable e-commerce system, developed using Java and Spring Boot.

## Table of Contents

- [Project Overview](#project-overview)
- [Installation](#installation)
- [Structure](#structure)
- [Features](#features)
- [Technologies](#technologies)


## Project Overview

**Context**:  

The SecurePro API delivers a comprehensive solution for efficient management of products, categories, and users, incorporating advanced role-based access control. Designed specifically for e-commerce platforms, it empowers administrators to seamlessly manage the product catalog while providing users with a secure and intuitive interaction experience.

**Objectives**:

- Build a secure REST API with session-based authentication using Spring Security.
- Implement role-based access control with distinct ADMIN and USER roles.
- Enable CRUD operations for products, categories, and users.
- Provide efficient pagination, sorting, and filtering for product and category data.
- Handle security, validation, and exceptions robustly.
- Use Docker and Jenkins for deployment workflows.


## Installation

### Prerequisites

- Java 8 or higher
- Apache Maven
- MySQL Server

### Steps

1. **Clone the repository:**

   ```sh
   git clone https://github.com/JavaAura/Med_HACHAMI_S4_B1_SecurePro
   cd SecurePro/

2. **Build the application:**
   ```sh
   mvn clean install

3. **Environment Variables:**  
   `URL_PROD`  
   `DRIVER_NAME`  
   `DB_USERNAME_PROD`
   `DB_PASSWORD_PROD`
   `APP_PORT`
   `DB_NAME_PROD`


4. **Run the application:**
  
   ```sh
   mvn spring-boot:run 
5. **Access the application :**
  - Default Port: http://localhost:8085

## Structure

- **Entities**:  
  Defines JPA entities such as `User`, `Product`, and `Category`, and their relationships (e.g., many-to-one or many-to-many).

- **Repository Layer**:  
  Extend `JpaRepository` for data access and include custom query methods.

- **Service Layer**:  
  Contains business logic and orchestrates operations between the Controller and Repository layers.
  
- **Controller Layer**:  
  Implements REST endpoints for managing learners, trainers, classes, and training sessions using `@RestController` annotation.
  
- **Exceptions**:  
  Centralized exception handling for REST API responses.

- **Utilities**:  
  Common utility classes and methods.

- **Tests**:  
  Unit tests implemented with Mockito and JUnit.

## Features

1. **User Management**:
   - Role-based access (ADMIN, USER).
   - Admins can manage users, including assigning roles.

2. **Product Management**:
   - List of products with pagination, sorting, and filtering accessible by both ADMIN and USER roles.
   - Admin-only access for creating, updating, and deleting products.

3. **Category Management**:
   - Manage product categories with hierarchical relationships.
   - Admin-only access for creating, updating, and deleting categories.

4. **Security**:
   - Session-based authentication using Spring Security and MariaDB.
   - Prevents unauthorized access to protected resources.

5. **Deployment**:
   - Docker: The application can be containerized using Docker for easy deployment.
   - Jenkins: CI/CD pipelines for automated builds, tests, and deployments.
   - Kubernetes: Orchestrates containerized applications for scaling, load balancing, and managing deployments in clusters.


## Technologies

- **Java 8**: Core language used for development.
- **Apache Maven**: For dependency management and project build.
- **Spring Boot**: For creating the REST API and managing application configuration.
- **Spring Data JPA**: For database interactions and repository management.
- **Spring Security**: Secures the application with session-based authentication.
- **MySQL**: Relational database for production.
- **H2 Database**: In-memory database for development.
- **Hibernate**: ORM for database access and management.
- **JUnit**: For unit testing.
- **Mockito**: For mocking and testing services.
- **Lombok**: For reducing boilerplate code.
- **Docker**: For containerized deployment.
- **Jenkins**: For CI/CD pipeline management.
- **Kubernetes**: For managing and orchestrating containerized applications.
