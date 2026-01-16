package com.eirzarog.bookstore_management.entity.dtos;

import java.time.LocalDate;

// This DTO represents what you want to send back to the client. // the reason for BookMapper
// You never expose your entity directly
// You can hide internal fields (e.g., createdAt, updatedAt, internal IDs)
// You can add computed fields (e.g., discountedPrice)
// You can evolve your API without breaking clients


public record BookResponseDTO (
    Long id,
    String title,
    String author,
    double price,
    String isbnNumber,
    LocalDate publishedDate,
    String bookType
){}
