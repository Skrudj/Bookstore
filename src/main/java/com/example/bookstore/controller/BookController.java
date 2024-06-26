package com.example.bookstore.controller;

import com.example.bookstore.dto.request.BookRequestDto;
import com.example.bookstore.dto.request.BookSearchParameters;
import com.example.bookstore.dto.response.BookResponseDto;
import com.example.bookstore.exceptions.EntityNotFoundException;
import com.example.bookstore.mappper.BookMapper;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
@Tag(name = "Book Entity Endpoints")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    @Operation(
            summary = "Get all books",
            description = "Endpoint to get all books. Supports pagination"
    )
    public List<BookResponseDto> findAll(Pageable pageable) {
        return bookService
                .findAll(pageable)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by Id")
    public BookResponseDto findById(@PathVariable Long id) {
        Book book = bookService.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book by id " + id + " not found")
        );

        return bookMapper.toDto(book);
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search book",
            description = "Endpoint for searching books by severe parameters"
    )
    public List<BookResponseDto> search(@RequestBody BookSearchParameters params) {
        return bookService
                .search(params)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create book")
    public BookResponseDto save(@RequestBody @Valid BookRequestDto request) {
        Book book = bookMapper.toModel(request);
        return bookMapper.toDto(bookService.add(book));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update book")
    public void update(@PathVariable Long id, @RequestBody BookRequestDto request) {
        Book book = bookMapper.toModel(request);
        bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
