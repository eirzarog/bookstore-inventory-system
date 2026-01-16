package com.eirzarog.bookstore_management.entity.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record BookCreateDTO(
        @NotBlank(message = "Title is required")
        String title,

//        @NotBlank(message = "Author is required")
        String author,

//        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false)
        Double price,

//        @NotBlank(message = "IsbnNumber is required")
        String isbnNumber,

//        @NotBlank(message = "PublishedDate is required")
        LocalDate publishedDate,

//        @NotBlank(message = "BookType type is required")
        String bookType
) {}




