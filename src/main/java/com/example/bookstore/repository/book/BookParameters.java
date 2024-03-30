package com.example.bookstore.repository.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookParameters {
    TITLE("title"),
    ISBN("isbn"),
    AUTHOR("author"),
    DESCRIPTION("description"),
    PRICE("price"),
    BOOK_LOWER_PRICE("lower_price"),
    BOOK_UPPER_PRICE("upper_price");

    private final String key;
}
