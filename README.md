# Book Management Microservice

A simple Book Management microservice built with Java Spring Boot and PostgreSQL. This project demonstrates core backend development skills, including RESTful API design, database integration, and containerization.

## Candidate Information

- **Name:** Taufik Ardiansyah Putra
- **Role Applied:** Junior Java Developer
- **Repository:** `springboot-microservice-task-taufik`

## Tech Stack

- **Framework:** Java Spring Boot (Maven)
- **Database:** PostgreSQL
- **Containerization:** Docker & Docker Compose
- **Version Control:** Git
- **API Testing:** Postman (with environment variables, pre-request scripts, and test scripts)
- **API Documentation:** Swagger UI (SpringDoc OpenAPI)

## Features Implemented (CRUD)

| Method   | Endpoint          | Description                                 |
| :------- | :---------------- | :------------------------------------------ |
| `POST`   | `/api/books`      | Add a new book                              |
| `GET`    | `/api/books`      | Get all books                               |
| `GET`    | `/api/books/{id}` | Get a book by ID                            |
| `PUT`    | `/api/books/{id}` | Update a book by ID (full update)           |
| `PATCH`  | `/api/books/{id}` | Partial update of a book (e.g., title only) |
| `DELETE` | `/api/books/{id}` | Delete a book                               |

## Entity Schema

```json
{
"id": Long,
"title": String,
"author": String,
"isbn": String,
"publishedDate": Date
}
```

## Entity-Relationship (ER) Diagram

![ER Diagram](docs/er-diagram.png)

The `Book` entity maps to the `books` table with the following columns: `id` (PK, auto-generated), `title` (String, not null), `author` (String, not null), `isbn` (String, not null, unique), and `published_date` (Date).

## API Documentation (Swagger UI)

Once the application is running, access the auto-generated API documentation at:

- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

## How to Run the Project

### Prerequisites

- Docker and Docker Compose installed on your machine.
- JDK 17+ (If running locally without Docker).
- Maven (If running locally without Docker).

### Running with Docker Compose (Recommended)

This approach will automatically build the Spring Boot application and spin up the PostgreSQL database container.

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/springboot-microservice-task-taufik.git
   cd springboot-microservice-task-taufik
   ```

2. Build and start the containers:

   ```bash
   docker-compose up -d --build

   ```

3. The application will be accessible at: `http://localhost:8080`

### Environment Variables

If you wish to run the app locally without Docker, configure the following environment variables or update the `src/main/resources/application.yml` file:

- `SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/bookdb`
- `SPRING_DATASOURCE_USERNAME=postgres`
- `SPRING_DATASOURCE_PASSWORD=postgres`

## API Testing & Postman Collection

A Postman collection with environment variables, pre-request scripts, and test scripts is included in this repository to easily test all endpoints.

- **Collection File:** `docs/postman_collection.json`
- **Environment File:** `docs/postman_environment.json`
- **How to use:**
  1. Open Postman -> Click `Import` -> Select both JSON files.
  2. Select the `Book Management API - Local` environment from the environment dropdown.
  3. Run the requests in sequence (POST first to create a book, then GET, PUT, PATCH, DELETE).
  4. Test scripts will automatically validate response status codes and body structure.

## Future Enhancements

While this project focuses on a single microservice, in a real-world scenario, this service could easily be integrated into a larger event-driven architecture (e.g., publishing events via message brokers) and orchestrated using platforms like Kubernetes for automated scaling and resilience.
