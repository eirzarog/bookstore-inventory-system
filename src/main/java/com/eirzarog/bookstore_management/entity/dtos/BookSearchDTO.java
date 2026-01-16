package com.eirzarog.bookstore_management.entity.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record BookSearchDTO (

    // Search
    @Schema(description = "Filter by title")
    String title,
    @Schema(description = "Filter by author")
    String author,
    // Pagination
    @Schema(description = "Page number")
    @Min(0)
    Integer page, // Default values prevent null issues
    @Schema(description = "Page size")
    @Min(1)
    @Max(100)
    Integer size,
    // Sorting
    @Schema(description = "Sort field")
    String sortBy,
    @Schema(description = "Sort direction (asc/desc)")
    String sortDir,
    // Filter
    @Schema(description = "Minimum price")
    Double minPrice,
    @Schema(description = "Maximum price")
    Double maxPrice,
    @Schema(description = "Book type (HARDCOVER, PAPERBACK, EBOOK)")
    String bookType
    )
{
    public BookSearchDTO {
        if (page == null) page = 0;
        if (size == null) size = 10;
        if (sortBy == null) sortBy = "id";
        if (sortDir == null) sortDir = "asc";
    }
}
