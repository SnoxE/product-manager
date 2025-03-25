# Product Manager

## Overview

The Product Manager is a web application that allows users to register and log in to access 
a variety of product-related features. Once logged in, users can browse all available products, 
view product details, and filter products by category.

### Features
- User Registration & Login: Users can register an account and log in to access the application.
- Product Browsing: Users can view a list of all available products.
- Product Filtering: Products can be filtered by category.
- Product Details: Users can view detailed information about a product.
- Admin Features: Admin users have additional permissions to:
    - Add new products
    - Update existing products
    - Delete products

## Getting Started

To get the application up and running locally, follow the steps below.

### Prerequisites

Before you can start the application, make sure you have the following installed:

- Java 21 or later: This application is built using Java.
- Gradle: The build tool used to manage dependencies and run the application.
- Docker: The application uses Docker, which is responsible for running the MongoDB container.

### 1. Clone the Repository

```bash
git clone https://github.com/SnoxE/product-manager.git
cd product-manager
```

### 2. Set Up MongoDB Using Docker

If you have Docker installed, you can use the following command to start MongoDB in 
a Docker container and initialize it with default data. The volumes needed for data persistence
will be created automatically, if they are not present in the system.

```bash
cd utils
docker-compose up -d
```

After development or testing is done, the container can be stopped using the command below.

```bash
docker-compose down
```

### 3. Generate and Add a Private Key to Project

```bash
openssl genpkey -algorithm RSA -out private.pem -aes256
```

After the key is generated it needs to be moved into following directory: `src/main/resources/static/certs/`.

### 4. Setup MongoDB URI

Create `secrets.properties` file in `/resources` for example using: `touch 
src/main/resources/secrets.properties` (the path will differ, depending on the current path).

Then add MongoDB URI Configuration (example URI matching the database running in the container below):

```bash
spring.data.mongodb.uri=mongodb://admin:password@localhost:27017/product-manager?authSource=admin
```

### 5. Build Project

This project uses Gradle for building and managing dependencies. To build the project and 
download the necessary dependencies, run:

```bash
./gradlew build
```

If the build fails due to formatting errors, running the formatter is going to be necessary. 
It can be run with the use of following command:

```bash
./gradlew spotlessApply
```

### 6. Run the Application

To start the application locally, run the following command:

```bash
./gradlew bootRun
```

This command will start the Spring Boot application and if the process is successful, it will
be accessible at:

```bash
http://localhost:8080
```

## API Documentation

The API documentation was created using Swagger UI, which is a great tool for quickly visualising all 
REST endpoints available in the app, grouped by their respective controller.
Once the application is running, you can view the API documentation, 
including all available endpoints, at:

```bash
http://localhost:8080/swagger-ui/index.html
```

## Running Tests with Gradle

Tests in the application can be run with following command:

```bash
./gradlew test
```

This will run all the tests that are defined in the application. 
Only selected tests can be run with the addition of `--tests` flag, as follows:

```bash
./gradlew test --tests "com.example.rest_of_the_path_to_selected_test_class"
```

The result of the tests is available in the console, however for more detailed test report, 
visit following directory `build/reports/tests/test/index.html`.




