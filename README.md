

# E-commerce Product Management System

## Description

This project is a web-based application for managing products and categories in an e-commerce system. It provides functionalities to add, update, delete, and view products and categories.

## Features

- **Category Management:** Users can add, update, delete, and view product categories.
- **Product Management:** Users can add, update, delete, and view products, associating them with categories.
- **Exception Handling:** Global exception handling is implemented to handle unexpected errors gracefully.
- **User Interface:** The application provides a user-friendly interface for interacting with the product and category management functionalities.

## Technologies Used

- **Spring Boot:** Framework for building web applications with Java.
- **Spring Data JPA:** Simplifies database access with Java Persistence API.
- **Thymeleaf:** Template engine for server-side rendering of HTML pages.
- **Bootstrap:** Front-end framework for designing responsive and mobile-first websites.
- **MySQL:** Relational database management system for storing product and category data.

## Getting Started

To run this project locally, follow these steps:

1. Clone this repository to your local machine.
2. Configure MySQL database settings in the `application.properties` file.
3. Build the project using Maven.
4. Run the Spring Boot application.
5. Access the application in your web browser at `http://localhost:8080/landing`.

## Usage

- Navigate to the home page to view existing categories and products.
- Use the "Add Category" button to add new categories.
- Use the "Add Product" button to add new products, associating them with categories.

## Endpoints

### Category Controller

| Method   | Endpoint            | Description                    |
|----------|---------------------|--------------------------------|
| POST     | `/api/categories`   | Creates a new category.        |
| PUT      | `/api/categories/{id}` | Updates an existing category. |
| DELETE   | `/api/categories/{id}` | Deletes a category by its ID. |
| GET      | `/api/categories/{id}` | Retrieves a category by its ID. |
| GET      | `/api/categories?page=3`   | Retrieves all categories with pagination support. |

### Product Controller

| Method   | Endpoint            | Description                               |
|----------|---------------------|-------------------------------------------|
| POST     | `/api/products/{catid}` | Creates a new product associated with a category. |
| PUT      | `/api/products/{id}`    | Updates an existing product.              |
| DELETE   | `/api/products/{id}`    | Deletes a product by its ID.              |
| GET      | `/api/products/{id}`    | Retrieves a product by its ID.            |
| GET      | `/api/products/?page=2`      | Retrieves all products with pagination support.  |


