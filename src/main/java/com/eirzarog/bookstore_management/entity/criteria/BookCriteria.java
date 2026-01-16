package com.eirzarog.bookstore_management.entity.criteria;

import com.eirzarog.bookstore_management.entity.Book;
import com.eirzarog.bookstore_management.entity.dtos.BookSearchDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookCriteria {

    public static Specification<Book> withFilters(BookSearchDTO dto) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (dto.title() != null && !dto.title().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + dto.title().toLowerCase() + "%"));
            }

            if (dto.author() != null && !dto.author().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("author")), "%" + dto.author().toLowerCase() + "%"));
            }

            if (dto.minPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), dto.minPrice()));
            }

            if (dto.maxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), dto.maxPrice()));
            }

            if (dto.bookType() != null) {
                predicates.add(cb.equal(root.get("bookType"), dto.bookType()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
