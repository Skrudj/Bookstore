package com.example.bookstore.mappper;

import com.example.bookstore.config.MapperConfig;
import com.example.bookstore.dto.request.BookRequestDto;
import com.example.bookstore.dto.response.BookResponseDto;
import com.example.bookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookResponseDto toDto(Book book);

    Book toModel(BookRequestDto request);
}
