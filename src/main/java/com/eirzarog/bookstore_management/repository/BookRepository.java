package com.eirzarog.bookstore_management.repository;

import com.eirzarog.bookstore_management.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    // Dynamic search with optional parameters for title and author
    @Query("""
    SELECT b FROM Books b
    WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
      AND (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')))
      AND (:minPrice IS NULL OR b.price >= :minPrice)
      AND (:maxPrice IS NULL OR b.price <= :maxPrice)
      AND (:bookType IS NULL OR LOWER(b.bookType) = LOWER(:bookType))
""")
    Page<Book> searchBooks(
            String title,
            String author,
            Double minPrice,
            Double maxPrice,
            String bookType,
            Pageable pageable
    );

    boolean existsByTitle(String title);

}
