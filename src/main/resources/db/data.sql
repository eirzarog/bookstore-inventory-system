INSERT INTO BOOKS (TITLE,
                   AUTHOR,
                   ISBN_NUMBER,
                   PUBLISHED_DATE,
                   PRICE,
                   BOOK_TYPE) VALUES
                                  ('Clean Code', 'Robert C. Martin', '9780132350884', '2008-08-01', 29.99, 'HARDCOVER'),
                                  ('The Pragmatic Programmer', 'Andrew Hunt, David Thomas', '9780201616224', '1999-10-30', 24.50, 'SOFTCOPY'),
                                  ('Design Patterns', 'Erich Gamma et al.', '9780201633610', '1994-10-21', 34.95, 'HARDCOVER'),
                                  ('You Don''t Know JS', 'Kyle Simpson', '9781491904244', '2015-12-27', 14.99, 'EBOOK'),
                                  ('Effective Java', 'Joshua Bloch', '9780134685991', '2018-01-06', 39.99, 'SOFTCOPY'),
                                  ('Refactoring', 'Martin Fowler', '9780201485677', '1999-07-08', 32.00, 'HARDCOVER'),
                                  ('Java Concurrency in Practice', 'Brian Goetz', '9780321349606', '2006-05-19', 28.75, 'SOFTCOPY'),
                                  ('Introduction to Algorithms', 'Cormen, Leiserson, Rivest, Stein', '9780262033848', '2009-07-31', 49.99, 'HARDCOVER'),
                                  ('Cracking the Coding Interview', 'Gayle Laakmann McDowell', '9780984782857', '2015-07-01', 19.99, 'EBOOK'),
                                  ('Spring in Action', 'Craig Walls', '9781617294945', '2018-10-05', 27.50, 'SOFTCOPY');

-- SELECT * FROM BOOKS