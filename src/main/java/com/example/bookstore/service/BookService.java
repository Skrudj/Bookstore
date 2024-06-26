package com.example.bookstore.service;

import com.example.bookstore.dto.request.BookSearchParameters;
import com.example.bookstore.model.Book;
import java.util.List;

public interface BookService extends GenericService<Book> {
    List<Book> search(BookSearchParameters searchParameters);
}
