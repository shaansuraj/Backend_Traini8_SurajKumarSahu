# Traini8 - Government Funded Training Centers Registry

Traini8 is an MVP for a registry of government-funded training centers. The application is built with **Spring Boot, Spring Data JPA, PostgreSQL (or H2 for development), and Maven**. It provides **REST APIs** to create and retrieve training center information and can be tested using **Postman**.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Technologies Used](#technologies-used)
3. [Prerequisites](#prerequisites)
4. [Installation and Setup](#installation-and-setup)
   - [Option A: Cloning via Git](#option-a-cloning-via-git)
   - [Option B: Downloading the ZIP File](#option-b-downloading-the-zip-file)
5. [Database Configuration](#database-configuration)
   - [Option A: Using PostgreSQL](#option-a-using-postgresql)
   - [Option B: Using H2 In-Memory Database](#option-b-using-h2-in-memory-database)
6. [Building the Application](#building-the-application)
7. [Running the Application](#running-the-application)
   - [Option A: Using Maven](#option-a-using-maven)
   - [Option B: Running the Executable JAR](#option-b-running-the-executable-jar)
8. [Testing the API Using Postman](#testing-the-api-using-postman)
   - [Testing the POST Endpoint](#testing-the-post-endpoint)
   - [Testing the GET Endpoint](#testing-the-get-endpoint)
9. [Troubleshooting](#troubleshooting)

---

## Project Overview

Traini8 allows for the registration and retrieval of government-funded training centers using a RESTful API. Users can **add training centers** with details such as **name, code, address, student capacity, and courses offered** and later retrieve this information.

## Technologies Used

- **Java 17**
- **Spring Boot 3.0.5**
- **Spring Data JPA**
- **PostgreSQL (Production) / H2 (Development)**
- **Maven**
- **Postman (for API testing)**

## Prerequisites

Before setting up the project, ensure you have the following installed:

- **JDK 17 or later**
- **Maven 3.8+**
- **PostgreSQL (if using PostgreSQL database)**
- **Git** (for cloning the repository, optional)
- **Postman** (for testing the API endpoints)

## Installation and Setup

### Option A: Cloning via Git

If you have Git installed, you can clone the repository:

```bash
git clone https://github.com/your-username/Traini8.git
cd Traini8
```

### Option B: Downloading the ZIP File

If you prefer, you can download the ZIP file of the repository:

1. Go to the **GitHub repository**.
2. Click on the **"Code"** button.
3. Select **"Download ZIP"**.
4. Extract the ZIP file.
5. Open a terminal and navigate to the extracted folder:
   
   ```bash
   cd Traini8
   ```

## Database Configuration

### Option A: Using PostgreSQL(I am using PostgreSQL in this application)

**Install and Start PostgreSQL:**
- Ensure PostgreSQL is installed and running.

**Create the Database:**
- Open a terminal and run:

```bash
psql -U postgres -h localhost
CREATE DATABASE traini8;
\q
```

**Configure the Connection:**
- Edit the file `src/main/resources/application.properties` and set:

```properties
# PostgreSQL configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/traini8
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Optional: SQL logging
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
```

- Replace `your_db_username` and `your_db_password` with your PostgreSQL credentials.

### Option B: Using H2 In-Memory Database

**Add H2 Dependency:**
- Ensure the following dependency is present in `pom.xml`:

```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```

**Configure H2:**
- Edit `src/main/resources/application.properties` with:

```properties
# H2 In-Memory Database configuration
spring.datasource.url=jdbc:h2:mem:traini8db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Enable H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## Building the Application

From the project root directory, build the project with:

```bash
mvn clean package
```

This command compiles the code and packages the application as an executable JAR in the `target` directory.

## Running the Application

### Option A: Using Maven

Run the application directly with:

```bash
mvn spring-boot:run
```

### Option B: Running the Executable JAR

After building, run the JAR file:

```bash
java -jar target/Traini8-0.0.1-SNAPSHOT.jar
```

> **Note:** If port **8080** is already in use, change the port in `application.properties`:
>
> ```properties
> server.port=8081, I am using 5000 in this application
> ```

## Testing the API Using Postman

### Testing the POST Endpoint

1. **Create a New Request in Postman:**
   - **Method:** `POST`
   - **URL:** `http://localhost:8080/api/training-centers`

2. **Set Headers:**
   - `Content-Type: application/json`

3. **Set the Request Body (raw JSON):**

   ```json
   {
     "centerName": "Alpha Training",
     "centerCode": "ABC123DEF456",
     "address": {
       "detailedAddress": "123 Main Street, Suite 400",
       "city": "Metropolis",
       "state": "NY",
       "pincode": "123456"
     },
     "studentCapacity": 150,
     "coursesOffered": ["Java", "Spring Boot", "SQL"],
     "contactEmail": "contact@alphatraining.com",
     "contactPhone": "+11234567890"
   }
   ```

4. **Send the Request.**
5. You should receive a **201 Created** response with the new training center details.

### Testing the GET Endpoint

1. **Create a New Request in Postman:**
   - **Method:** `GET`
   - **URL:** `http://localhost:8080/api/training-centers`

2. **Send the Request.**
3. You should receive a **200 OK** response with a list of training centers.

## Troubleshooting

- **Port Already in Use:** If you see an error about port **8080** being in use, either **stop the conflicting process** or change the port in `application.properties` (`server.port=8081 or any port you like(it should be free)`).
- **Database Connection Issues:** Verify that PostgreSQL is running and the `traini8` database exists.
- **Postman URL Issues:** If `%0A` (newline) appears in your URL, manually type the URL in Postman to avoid extra characters(Sometimes you might need to create a new request as well).

## Attachments

Screenshots for **POST** and **GET** requests tested using Postman are included in the `screenshots/` folder.

