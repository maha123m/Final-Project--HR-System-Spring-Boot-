# E-commerce System - Final Project

## Project Overview

This project implements an **E-commerce System** using the **Spring Boot** framework. The application is designed with a modular and layered architecture to manage various entities like **Addresses**, **Authentication**, **Carts**, **Categories**, **Orders**, and **Products**. It ensures scalability, maintainability, and clean separation of concerns between the layers.

---

## Application Architecture

The system is structured into three main layers, ensuring efficient data flow and clear separation of responsibilities:

1. **Controller Layer**:
   - Manages incoming HTTP requests from the browser or client.
   - Maps client requests to the appropriate service methods.
   - Returns processed responses to the client.

2. **Service Layer**:
   - Contains the core business logic of the application.
   - Acts as an intermediary between the Controller and Repository layers.

3. **Repository Layer**:
   - Handles database interactions for CRUD operations.
   - Manages data persistence and retrieval for the entities.

### Data Flow
1. A **Browser/Client** sends an HTTP request to the **Controller Layer**.
2. The **Controller Layer** forwards the request to the appropriate method in the **Service Layer**.
3. The **Service Layer** processes the request and interacts with the **Repository Layer** for database operations.
4. The processed data is returned through the same layers, back to the client.

---

## API Endpoints for Category Management

The system provides a set of RESTful APIs to manage product categories efficiently. Below is a detailed list of the available endpoints:

| **API Name**        | **Endpoint**                             | **Method** | **Purpose**                        | **Request Body** | **Request Parameters**             | **Response**         |
|---------------------|-----------------------------------------|------------|------------------------------------|------------------|-------------------------------------|-----------------------|
| Create Category     | `/api/admin/category`                   | POST       | Create a new product category      | `Category`       | None                                | `CategoryDTO`         |
| Get Categories      | `/api/public/categories`                | GET        | Retrieve a list of product categories | None             | `pageNumber`, `pageSize`, `sortBy`, `sortOrder` | `CategoryResponse`    |
| Update Category     | `/api/admin/categories/{categoryId}`    | PUT        | Update an existing product category | `Category`       | `categoryId`                        | `CategoryDTO`         |
| Delete Category     | `/api/admin/categories/{categoryId}`    | DELETE     | Delete an existing product category | None             | `categoryId`                        | `CategoryDTO`         |

---

### Key Features of the API

1. **Create Category**:
   - Allows admins to add new product categories.
   - Requires a `Category` object in the request body.

2. **Retrieve Categories**:
   - Public endpoint for fetching a paginated and sorted list of product categories.
   - Supports query parameters such as `pageNumber`, `pageSize`, `sortBy`, and `sortOrder`.

3. **Update Category**:
   - Enables admins to modify existing categories by specifying the `categoryId` in the URL.
   - Updated data is sent in the request body.

4. **Delete Category**:
   - Allows admins to delete a specific category by providing the `categoryId` in the URL.

---

## Technologies Used
- **Framework**: Spring Boot
- **Database**: Relational Database (e.g., MySQL, PostgreSQL)
- **Architecture**: Layered (Controller, Service, Repository)
- **REST APIs**: Well-designed endpoints for modular management of E-commerce functionalities.
