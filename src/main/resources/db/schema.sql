-- http://localhost:8080/h2-console

CREATE TABLE BOOKS (
                       ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                       TITLE VARCHAR(255) NOT NULL,
                       AUTHOR VARCHAR(255) NOT NULL,
                       ISBN_NUMBER VARCHAR(50) NOT NULL,
                       PUBLISHED_DATE DATE NOT NULL,
                       PRICE DECIMAL(10,2) NOT NULL,
                       BOOK_TYPE VARCHAR(20) NOT NULL
);

