package com.example.bookstore.dto.request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class BookRequestDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
