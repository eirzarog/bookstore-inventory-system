package com.eirzarog.bookstore_management.entity;

import com.eirzarog.bookstore_management.entity.enums.BookType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@Entity(name = "Books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbnNumber;
    private LocalDate publishedDate;
    private Double price;

    @Enumerated(EnumType.STRING)
    private BookType bookType;



}
