package com.example.bookstore.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {
    T add(T entity);

    List<T> findAll();

    Optional<T> findById(Long id);

    void update(Long id, T entity);

    void delete(Long id);
}
