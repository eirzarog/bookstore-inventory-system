package com.eirzarog.bookstore_management.entity.mappers;

// A mapper keeps your service layer clean.
// For later:
// You can also use MapStruct later, but this manual mapper is perfect for now.
// A unified BookMapper with MapStruct

// Why a mapper?
//You never expose your entity directly
//You can hide internal fields (e.g., createdAt, updatedAt, internal IDs)
//You can add computed fields (e.g., discountedPrice)
//You can evolve your API without breaking clients
//You keep your controller and service clean and maintainable
// We use it in the BookController to convert between Book and BookResponseDTO
// and vice versa.
// We use it in the BookService to convert between Book and BookResponseDTO
// and vice versa.

import com.eirzarog.bookstore_management.entity.Book;
import com.eirzarog.bookstore_management.entity.enums.BookType;
import com.eirzarog.bookstore_management.entity.dtos.BookResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookResponseDTO dto) {
        if (dto == null) return null;
        return Book.builder()
                .id(dto.id())
                .title(dto.title())
                .author(dto.author())
                .price(dto.price())
                .isbnNumber(dto.isbnNumber())
                .publishedDate(dto.publishedDate())
                .bookType(BookType.valueOf(dto.bookType()))
                .build();
    }

    public BookResponseDTO toDTO(Book book) {
        if (book == null) return null;
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getIsbnNumber(),
                book.getPublishedDate(),
                book.getBookType().name()
        );

    }
}
