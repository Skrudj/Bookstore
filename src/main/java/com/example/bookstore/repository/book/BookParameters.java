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
    BOOK_LOWER_PRICE("lowerPrice"),
    BOOK_UPPER_PRICE("upperPrice");

    private final String key;
}
