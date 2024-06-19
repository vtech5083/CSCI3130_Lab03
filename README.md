# Book API Lab

This project is a simple Spring Boot application that provides a RESTful API for managing a collection of books. It includes CRUD operations and integrates with a MySQL database.

## Project Structure

- **Model**: Represents the Book entity.
- **Repository**: Provides database access methods.
- **Service**: Defines business logic and CRUD operations.
- **Controller**: Exposes REST endpoints.

## Dependencies

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- MySQL Connector Java
- H2 Database (for testing)
- Spring Boot Starter Actuator (for monitoring)

## Getting Started

### Prerequisites

- Java 17
- Maven
- MySQL database

### Installation

1. Clone the repository:
    ```sh
    git clone <repository-url>
    cd bookApiLab
    ```

2. Update the `application.properties` file with your MySQL database configuration:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/book_db
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## API Endpoints

- **Create a new book**
    ```sh
    POST /api/books
    ```

- **Get all books**
    ```sh
    GET /api/books
    ```

- **Get a book by ID**
    ```sh
    GET /api/books/{id}
    ```

- **Update a book**
    ```sh
    PUT /api/books/{id}
    ```

- **Delete a book**
    ```sh
    DELETE /api/books/{id}
    ```

## Testing

You can use Postman or a similar tool to test the API endpoints. Ensure your MySQL database is running and properly configured in the `application.properties` file.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'feat: add some feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

