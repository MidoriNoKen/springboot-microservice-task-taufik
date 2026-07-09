package com.taufik.bookmanagement.controller;

import com.taufik.bookmanagement.dto.ErrorResponse;
import com.taufik.bookmanagement.entity.Book;
import com.taufik.bookmanagement.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Books", description = "CRUD operations for managing books")
public class BookController {

    private final BookService bookService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a new book",
            description = "Adds a new book to the catalog. All required fields must be provided. ISBN must be unique."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Book created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Book.class),
                            examples = @ExampleObject(
                                    name = "CreatedBook",
                                    value = """
                                            {
                                              "id": 1,
                                              "title": "The Pragmatic Programmer",
                                              "author": "Andrew Hunt & David Thomas",
                                              "isbn": "978-0-13-595705-9",
                                              "publishedDate": "1999-10-30"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed (missing/invalid fields)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "ValidationError",
                                    value = """
                                            {
                                              "status": 400,
                                              "message": "title: Title is required",
                                              "timestamp": "2026-07-09T08:30:00"
                                            }
                                            """
                            )
                    )
            )
    })
    public ResponseEntity<Book> createBook(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Book payload to create",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Book.class),
                            examples = @ExampleObject(
                                    name = "NewBook",
                                    value = """
                                            {
                                              "title": "The Pragmatic Programmer",
                                              "author": "Andrew Hunt & David Thomas",
                                              "isbn": "978-0-13-595705-9",
                                              "publishedDate": "1999-10-30"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Book book
    ) {
        Book created = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get all books",
            description = "Returns the full list of books currently stored in the catalog."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of books retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Book.class))
                    )
            )
    })
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get a book by ID",
            description = "Retrieves a single book by its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Book found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Book.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "NotFound",
                                    value = """
                                            {
                                              "status": 404,
                                              "message": "Book not found with id: 99",
                                              "timestamp": "2026-07-09T08:30:00"
                                            }
                                            """
                            )
                    )
            )
    })
    public ResponseEntity<Book> getBookById(
            @Parameter(description = "Unique ID of the book", example = "1", required = true)
            @PathVariable Long id
    ) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Full update of a book (PUT)",
            description = "Replaces all updatable fields of an existing book. All required fields must be provided."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Book updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Book.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<Book> updateBook(
            @Parameter(description = "Unique ID of the book to update", example = "1", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Full book payload for replacement",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Book.class),
                            examples = @ExampleObject(
                                    name = "FullUpdate",
                                    value = """
                                            {
                                              "title": "Clean Code",
                                              "author": "Robert C. Martin",
                                              "isbn": "978-0-13-235088-4",
                                              "publishedDate": "2008-08-01"
                                            }
                                            """
                            )
                    )
            )
            @Valid @RequestBody Book bookDetails
    ) {
        Book updated = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Partial update of a book (PATCH)",
            description = "Updates only the fields provided in the request body. Omitted fields remain unchanged."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Book partially updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Book.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid field or value",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<Book> partialUpdate(
            @Parameter(description = "Unique ID of the book to update", example = "1", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Partial JSON object with fields to update (e.g. title only)",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    type = "object",
                                    example = "{\"title\": \"Updated Title via PATCH\"}"
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "UpdateTitleOnly",
                                            summary = "Update title only",
                                            value = "{\"title\": \"Updated Title via PATCH\"}"
                                    ),
                                    @ExampleObject(
                                            name = "UpdateAuthorAndDate",
                                            summary = "Update author and publishedDate",
                                            value = """
                                                    {
                                                      "author": "Martin Fowler",
                                                      "publishedDate": "2018-11-01"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @RequestBody Map<String, Object> updates
    ) {
        Book updated = bookService.partialUpdate(id, updates);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a book",
            description = "Permanently removes a book from the catalog by its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book deleted successfully (no content)"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "Unique ID of the book to delete", example = "1", required = true)
            @PathVariable Long id
    ) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
