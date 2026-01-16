package com.eirzarog.bookstore_management.entity.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

public record PageResponseDTO<T>(
        @Schema(description = "Page content")
        List<T> content,

        // Pagination metadata
        @Schema(description = "Current page number")
        int page,
        @Schema(description = "Page size")
        int size,
        @Schema(description = "Total number of elements")
        long totalElements,
        @Schema(description = "Total number of pages")
        int totalPages,
        @Schema(description = "Is this the first page?")
        boolean first,
        @Schema(description = "Is this the last page?")
        boolean last,
        @Schema(description = "Is the result sorted?")
        boolean sorted,

        // UI metadata
        @Schema(description = "Is there a next page?")
        boolean hasNext,
        @Schema(description = "Is there a previous page?")
        boolean hasPrev,
        @Schema(description = "Start index of current page")
        long rangeStart,
        @Schema(description = "End index of current page")
        long rangeEnd,

        // Navigation links (HATEOAS-like) (preserve filters + sorting)
        @Schema(description = "Navigation links")
        Map<String, String> links,

        // Optional: echo back filters used in the request
        @Schema(description = "Filters applied to the query")
        Map<String, Object> filters
) {}

