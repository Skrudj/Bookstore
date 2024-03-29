package com.example.bookstore.controller;

import com.example.bookstore.dto.request.BookRequestDto;
import com.example.bookstore.dto.response.BookResponseDto;
import com.example.bookstore.exeptions.EntityNotFoundException;
import com.example.bookstore.mappper.BookMapper;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookResponseDto> getAll() {
        return bookService
                .findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public BookResponseDto findById(@PathVariable Long id) {
        Book book = bookService.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book by id " + id + " not found")
        );

        return bookMapper.toDto(book);
    }

    @PostMapping
    public BookResponseDto save(@RequestBody BookRequestDto request) {
        Book book = bookMapper.toModel(request);
        return bookMapper.toDto(bookService.add(book));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody BookRequestDto request) {
        Book book = bookMapper.toModel(request);
        bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
