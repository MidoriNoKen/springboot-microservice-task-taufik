package com.taufik.bookmanagement.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Book", description = "Book entity representing a catalog entry")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Unique identifier of the book (auto-generated)",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    @Schema(
            description = "Title of the book",
            example = "The Pragmatic Programmer",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String title;

    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    @Schema(
            description = "Author of the book",
            example = "Andrew Hunt & David Thomas",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String author;

    @NotBlank(message = "ISBN is required")
    @Column(nullable = false, unique = true)
    @Schema(
            description = "International Standard Book Number (must be unique)",
            example = "978-0-13-595705-9",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String isbn;

    @Column(name = "published_date")
    @Schema(
            description = "Publication date in ISO-8601 format (yyyy-MM-dd)",
            example = "1999-10-30",
            type = "string",
            format = "date"
    )
    private LocalDate publishedDate;
}
