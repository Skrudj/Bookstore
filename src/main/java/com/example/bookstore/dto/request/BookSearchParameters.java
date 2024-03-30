package com.example.bookstore.dto.request;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class BookSearchParameters {
    private String title;
    private String isbn;
    private String author;
    private String description;
    private List<String> tags;
    private BigDecimal lowerPrice;
    private BigDecimal upperPrice;
}
