package com.eirzarog.bookstore_management.entity.dtos;

import java.time.LocalDate;

public record BookUpdateDTO (

    String title,
    String author,
    double price,
    String isbnNumber,
    LocalDate publishedDate,
    String bookType
){}
