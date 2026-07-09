# Issue #4: Testing & Documentation — Postman, ER Diagram, README & Final Delivery

**Assigned to:** Junior Backend Developer  
**Priority:** Medium  
**Estimated Time:** 2 Hours

---

## Description

Finalize the project by thoroughly testing all endpoints with an optimized Postman collection, creating the ER diagram, and writing the project documentation (README.md). This is the final phase before delivery.

---

## Tasks

1. **Postman Collection (Optimized):** Test all endpoints using Postman with a professional setup:
   - Use **Postman Environment Variables** (e.g., `{{base_url}}`, `{{book_id}}`) for portability and security — do not hardcode values.
   - Add **Pre-request Scripts** to auto-set dynamic values (e.g., timestamps, generated IDs).
   - Add **Test Scripts** (Postman Tests tab) for each request to validate:
     - Response status code (e.g., 200, 201, 204, 404, 400)
     - Response body contains expected fields (e.g., `id`, `title`, `author`)
     - Data types of returned fields
   - Export the Postman Collection as `docs/postman_collection.json`.
   - Export the Postman Environment as `docs/postman_environment.json`.

2. **ER Diagram:** Create an Entity-Relationship diagram for the `Book` entity using dbdiagram.io, draw.io, or DBeaver. Export it as an image and save as `docs/er-diagram.png`.

3. **README.md:** Write the `README.md` file in the project root using the exact template provided in `instruction.md`. Must include:
   - Candidate Information (Name, Role, Repository)
   - Tech Stack (Spring Boot, PostgreSQL, Docker, Postman, Swagger)
   - Features Implemented table (all 6 endpoints)
   - Entity Schema
   - ER Diagram section with image reference
   - API Documentation section (Swagger UI and OpenAPI JSON URLs)
   - How to Run (Docker Compose + Local)
   - Environment Variables
   - API Testing & Postman Collection usage guide
   - Future Enhancements (Kubernetes mentioned here only)

4. **Final Commit & Push:** Commit all changes with descriptive commit messages and push to the GitHub repository.

---

## Acceptance Criteria

- [x] Postman Collection (`docs/postman_collection.json`) is exported with all 6 endpoint requests.
- [x] Postman Collection includes **Pre-request Scripts** that auto-set dynamic values.
- [x] Postman Collection includes **Test Scripts** that validate status codes, response body structure, and data types.
- [x] Postman Environment (`docs/postman_environment.json`) is exported with variables like `{{base_url}}` and `{{book_id}}`.
- [x] ER Diagram (`docs/er-diagram.png`) clearly shows the `Book` entity with all fields and their types.
- [x] `README.md` follows the required template exactly and is placed in the project root.
- [x] All changes are committed with meaningful commit messages and pushed to `springboot-microservice-task-taufik`.

---

## Technical Notes

- Postman Test Scripts use JavaScript. Example test snippet for reference:
  ```javascript
  pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
  });
  pm.test("Response has id field", function () {
    pm.response.to.have.jsonBody("id");
  });
  ```
- Postman Pre-request Scripts can use `pm.variables.set("timestamp", new Date().toISOString())` for dynamic values.
- Postman Environment variables should have both `initial` and `current` values set appropriately.
- The ER diagram can be a simple single-entity diagram showing the `book` table with columns: id (PK), title, author, isbn, published_date.
- Do **not** mention Kubernetes anywhere except in the "Future Enhancements" section of README.md.
- Ensure `.gitignore` includes `docs/` if it contains sensitive exported files — but in this case the Postman files do not contain secrets, so they should be committed.

---

## Definition of Done

- All 6 endpoints have been tested via Postman with automated test scripts passing.
- Postman collection and environment files are committed in `docs/`.
- ER diagram is committed in `docs/`.
- README.md is complete and matches the required template.
- The repository is fully pushed to GitHub with all final changes.
- The Junior Developer has notified the Project Manager that the project is ready for review.
