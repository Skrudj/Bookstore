package com.example.bookstore.service.impl;

import com.example.bookstore.dto.request.BookSearchParameters;
import com.example.bookstore.exceptions.EntityNotFoundException;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.book.BookRepository;
import com.example.bookstore.repository.book.BookSpecificationBuilder;
import com.example.bookstore.service.BookService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookSpecificationBuilder specificationBuilder;

    @Override
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).toList();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> search(BookSearchParameters params) {
        Specification<Book> specification = specificationBuilder.build(params);

        return bookRepository.findAll(specification);
    }

    @Override
    public void update(Long id, Book book) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book by id: " + id + " not found");
        }

        book.setId(id);
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
