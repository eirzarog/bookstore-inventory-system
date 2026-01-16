package com.eirzarog.bookstore_management.service;

import com.eirzarog.bookstore_management.entity.*;
import com.eirzarog.bookstore_management.entity.criteria.BookCriteria;
import com.eirzarog.bookstore_management.entity.dtos.*;
import com.eirzarog.bookstore_management.entity.enums.BookType;
import com.eirzarog.bookstore_management.entity.mappers.BookMapper;
import com.eirzarog.bookstore_management.entity.mappers.PaginationMapper;
import com.eirzarog.bookstore_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final PaginationMapper paginationMapper;

    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toDTO(book);
    }

    public BookResponseDTO saveBook(BookCreateDTO dto) { // instead of BookRequestDTO
        if (dto.title() != null && bookRepository.existsByTitle(dto.title())) {
            throw new RuntimeException("Book already exists");
        }

        // Convert DTO → Entity
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setPrice(dto.price());
        book.setBookType(BookType.valueOf(dto.bookType()));

        // Save entity
        Book savedBook = bookRepository.save(book);

        // Convert Entity → DTO
        return bookMapper.toDTO(savedBook);
    }

    public BookResponseDTO updateBook(Long id, BookUpdateDTO dto) { // instead of BookRequestDTO
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(dto.title());
        existingBook.setAuthor(dto.author());
        existingBook.setPrice(dto.price());
        existingBook.setIsbnNumber(dto.isbnNumber());
        existingBook.setPublishedDate(dto.publishedDate());
        existingBook.setBookType(BookType.valueOf(dto.bookType()));

        Book updated = bookRepository.save(existingBook);

        return bookMapper.toDTO(updated);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }
    // PaginationMapper + record DTOs + Specification API = clean pagination, immutable data, dynamic filtering
    public PageResponseDTO<BookResponseDTO> getBooks(BookSearchDTO searchDTO) {

        Sort.Direction direction = Sort.Direction
                .fromOptionalString(searchDTO.sortDir())
                .orElse(Sort.Direction.ASC);

        Pageable pageable = PageRequest.of(
                searchDTO.page(),
                searchDTO.size(),
                Sort.by(direction, searchDTO.sortBy())
        );

//        Page<BookResponseDTO> page = bookRepository
//                .searchBooks(searchDTO.getTitle(),
//                        searchDTO.getAuthor(),
//                        searchDTO.getMinPrice(),
//                        searchDTO.getMaxPrice(),
//                        searchDTO.getBookType(),
//                        pageable)
//                .map(bookMapper::toDTO);

        Specification<Book> spec = BookCriteria.withFilters(searchDTO); // criteria

        Page<BookResponseDTO> page = bookRepository
                .findAll(spec, pageable) // JpaSpecificationExecutor
                .map(bookMapper::toDTO);


        Map<String, Object> filters = new HashMap<>(); // HashMap (mutable) allows null values
        filters.put("title", searchDTO.title());
        filters.put("author", searchDTO.author());
        filters.put("minPrice", searchDTO.minPrice());
        filters.put("maxPrice", searchDTO.maxPrice());
        filters.put("bookType", searchDTO.bookType());
        filters.put("sortBy", searchDTO.sortBy());
        filters.put("sortDir", searchDTO.sortDir());


        return paginationMapper.toPageResponse(
                page,
                searchDTO.page(),
                searchDTO.size(),
                filters,
                "/api/books"
        );
    }


//    // With PaginationMapper
//    public PageResponseDTO<BookResponseDTO> getBooks(BookSearchDTO searchDTO) {
//
//        Sort.Direction direction = Sort.Direction
//                .fromOptionalString(searchDTO.getSortDir())
//                .orElse(Sort.Direction.ASC);
//
//        Pageable pageable = PageRequest.of(
//                searchDTO.getPage(),
//                searchDTO.getSize(),
//                Sort.by(direction, searchDTO.getSortBy())
//        );
//
//        Page<BookResponseDTO> page = bookRepository
//                .searchBooks(searchDTO.getTitle(),
//                             searchDTO.getAuthor(),
//                             searchDTO.getMinPrice(),
//                             searchDTO.getMaxPrice(),
//                             searchDTO.getBookType(),
//                             pageable)
//            .map(bookMapper::toDTO);
//
////        Map<String, Object> filters = Map.of(  // Map.of() → NO null keys, NO null values
////                "title", searchDTO.getTitle(),
////                "author", searchDTO.getAuthor(),
////                "minPrice", searchDTO.getMinPrice(),
////                "maxPrice", searchDTO.getMaxPrice(),
////                "bookType", searchDTO.getBookType(),
////                "sortBy", searchDTO.getSortBy(),
////                "sortDir", searchDTO.getSortDir()
////        );
//
//        Map<String, Object> filters = new HashMap<>(); // HashMap (mutable) allows null values
//        filters.put("title", searchDTO.getTitle());
//        filters.put("author", searchDTO.getAuthor());
//        filters.put("minPrice", searchDTO.getMinPrice());
//        filters.put("maxPrice", searchDTO.getMaxPrice());
//        filters.put("bookType", searchDTO.getBookType());
//        filters.put("sortBy", searchDTO.getSortBy());
//        filters.put("sortDir", searchDTO.getSortDir());
//
//
//        return paginationMapper.toPageResponse(
//                page,
//                searchDTO.getPage(),
//                searchDTO.getSize(),
//                filters,
//                "/api/books"
//        );
//    }



    // // Without PaginationMapper
    // // Sorting, Pagination, Search, Filtering, Mapping
    // public PageResponseDTO<BookResponseDTO> getBooks(BookSearchDTO searchDTO) {
    //
    //     Sort.Direction direction = Sort.Direction
    //             .fromOptionalString(searchDTO.getSortDir())
    //             .orElse(Sort.Direction.ASC);
    //
    //     Sort sort = Sort.by(direction, searchDTO.getSortBy());
    //
    //     Pageable pageable = PageRequest.of(
    //             searchDTO.getPage(),
    //             searchDTO.getSize(),
    //             sort
    //     );
    //
    //     // Query
    //     Page<Book> books = bookRepository.searchBooks(
    //             searchDTO.getTitle(),
    //             searchDTO.getAuthor(),
    //             searchDTO.getMinPrice(),
    //             searchDTO.getMaxPrice(),
    //             searchDTO.getBookType(),
    //             pageable
    //     );
    //
    //     // Convert content to DTOs
    //     List<BookResponseDTO> content = books.getContent()
    //             .stream()
    //             .map(bookMapper::toDTO)
    //             .toList();
    //
    //     // Build base query string (preserves filters + sorting)
    //     String baseQuery = buildQueryString(searchDTO);
    //
    //     // Build navigation links
    //     Map<String, String> links = new HashMap<>();
    //
    //     int current = books.getNumber();
    //     int size = books.getSize();
    //
    //     links.put("self", "/api/books?page=" + current + "&size=" + size + baseQuery);
    //
    //     links.put("next", books.isLast() ? null : "/api/books?page=" + (current + 1) + "&size=" + size + baseQuery);
    //     links.put("prev", books.isFirst() ? null : "/api/books?page=" + (current - 1) + "&size=" + size + baseQuery);
    //
    //     // UI metadata
    //     boolean hasNext = !books.isLast();
    //     boolean hasPrev = !books.isFirst();
    //
    //     long rangeStart = (long) current * size + 1;
    //     long rangeEnd = rangeStart + books.getNumberOfElements() - 1;
    //
    //     // Echo back filters used
    //     Map<String, Object> filters = new HashMap<>();
    //     filters.put("title", searchDTO.getTitle());
    //     filters.put("author", searchDTO.getAuthor());
    //     filters.put("minPrice", searchDTO.getMinPrice());
    //     filters.put("maxPrice", searchDTO.getMaxPrice());
    //     filters.put("bookType", searchDTO.getBookType());
    //     filters.put("sortBy", searchDTO.getSortBy());
    //     filters.put("sortDir", searchDTO.getSortDir());
    //
    //     return new PageResponseDTO<>(
    //             content,
    //             books.getNumber(),
    //             books.getSize(),
    //             books.getTotalElements(),
    //             books.getTotalPages(),
    //             books.isFirst(),
    //             books.isLast(),
    //             books.getSort().isSorted(),
    //             hasNext,
    //             hasPrev,
    //             rangeStart,
    //             rangeEnd,
    //             links,
    //             filters
    //     );
    // }
    //
    // // Helper: Build Query String (Preserves Filters + Sorting)
    // // This ensures every pagination link keeps the same filters and sorting
    // private String buildQueryString(BookSearchDTO dto) {
    //     StringBuilder sb = new StringBuilder();
    //
    //     if (dto.getTitle() != null) sb.append("&title=").append(dto.getTitle());
    //     if (dto.getAuthor() != null) sb.append("&author=").append(dto.getAuthor());
    //     if (dto.getMinPrice() != null) sb.append("&minPrice=").append(dto.getMinPrice());
    //     if (dto.getMaxPrice() != null) sb.append("&maxPrice=").append(dto.getMaxPrice());
    //     if (dto.getBookType() != null) sb.append("&bookType=").append(dto.getBookType());
    //
    //     sb.append("&sortBy=").append(dto.getSortBy());
    //     sb.append("&sortDir=").append(dto.getSortDir());
    //
    //     return sb.toString();
    // }

}

