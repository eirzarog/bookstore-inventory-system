package com.eirzarog.bookstore_management.controller;

import com.eirzarog.bookstore_management.entity.dtos.*;
import com.eirzarog.bookstore_management.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/*
Add Specification API for dynamic queries // later
Implement caching for frequently accessed data // later
Implement HATEOAS for better API navigation // later
Implement API versioning // later
Implement JWT-based authentication and authorization // later
Add global exception handling // later
*/

// Ambiguous mapping for two times declaring the same endpoint // ασαφής
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // unified endpoint
    // GET http://localhost:8080/api/books?size=2
    // ?page=0&size=2&sort=title,asc
    @Operation(
            summary = "Get paginated list of books",
            description = "Supports filtering, sorting, and pagination"
    )
    @GetMapping
    public PageResponseDTO<BookResponseDTO> getBooks(
            @Parameter(description = "Search and filter parameters")
            BookSearchDTO searchDTO) {
        return bookService.getBooks(searchDTO);
    }

    @GetMapping("/{id}")
    public BookResponseDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookResponseDTO addBook(@RequestBody BookCreateDTO bookCreateDTO) {
        return bookService.saveBook(bookCreateDTO);
    }

    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable Long id, @RequestBody BookUpdateDTO bookUpdateDTO) {
        return bookService.updateBook(id, bookUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
