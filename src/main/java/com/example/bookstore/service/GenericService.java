package com.example.bookstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface GenericService<T> {
    T add(T entity);

    List<T> findAll(Pageable pageable);

    Optional<T> findById(Long id);

    void update(Long id, T entity);

    void delete(Long id);
}
