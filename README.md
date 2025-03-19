# Ecommerce Mono Repo

This is a ecommerce-monorepo containing three Spring Boot microservices: **Order Service**, **User Service**, and **Product Service**, each providing CRUD operations.

## 1. Project Overview
The repository is structured as follows:
- **order-service**: Handles order creation, updates, deletion and retrieval.
- **user-service**: Manages user registration and login.
- **product-service**: Manages product data.

Each service is independent and follows microservices architecture principles.

## 2. Prerequisites
Before running this project, ensure that the following software is installed on your system:
- **Java 21**
- **Maven** or **Gradle**
- **Docker** and **Docker Compose**

## 3. How to Run the Project

### 3.1 Local Setup
Follow these steps to set up and run the project locally:

1. **Clone the repository and navigate to the project directory**:
   ```bash
   git clone https://github.com/milica007/ecommerce-monorepo.git
   cd ecommerce-monorepo
   ```
2. **Build the entire project**
   ```bash
   ./gradlew build
   ```
3. **Run individual microservices**
   ```bash
   cd order-service && ./gradlew bootRun &
   cd ../user-service && ./gradlew bootRun &
   cd ../product-service && ./gradlew bootRun &
   ```

### 3.2 Run Databases with Docker
This project uses Docker only for database services and **pgAdmin**. To start them, follow these steps:

1. **Ensure Docker and Docker Compose are installed and running.**
2. **Run the database services and pgAdmin:**
   ```bash
   docker-compose up -d
   ```
3. **Database Instances:**
   - **Order Database:** `localhost:5433` (DB: `order_db`)
   - **Product Database:** `localhost:5434` (DB: `product_db`)
   - **User Database:** `localhost:5435` (DB: `user_db`)
4. **pgAdmin:** Access at [http://localhost:8543](http://localhost:8543)
   - **Username:** `postgres`
   - **Password:** `postgres`

## 4. How to Run Tests
To run all tests across services, use the following command:
```bash
./gradlew test
```

To run tests for a specific service:
```bash
cd order-service && ./gradlew test
cd ../user-service && ./gradlew test
cd ../product-service && ./gradlew test
```

## 5. Project Structure
Below is an overview of the main directories and files in the project:
- **order-service**: Contains the source code and tests for the Order Service.
- **user-service**: Contains the source code and tests for the User Service.
- **product-service**: Contains the source code and tests for the Product Service.
- **docker-compose.yml**: Configuration file for running PostgreSQL databases and pgAdmin.
- **init-scripts**: Directory with SQL scripts used to initialize the test databases.

Each service has its own database schema and runs independently, but they communicate via REST APIs and potentially Kafka for asynchronous messaging.
