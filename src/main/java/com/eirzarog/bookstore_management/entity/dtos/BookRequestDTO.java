package com.eirzarog.bookstore_management.entity.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequestDTO {

    private String title;
    private String author;
    private double price;
    private String isbnNumber;
    private LocalDate publishedDate;
    private String bookType;
}
