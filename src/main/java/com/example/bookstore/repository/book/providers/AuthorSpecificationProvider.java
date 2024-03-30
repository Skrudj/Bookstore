package com.example.bookstore.repository.book.providers;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.SpecificationProvider;
import com.example.bookstore.repository.book.BookParameters;
import java.util.Arrays;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private final String key = BookParameters.AUTHOR.getKey();

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get(key).in(Arrays.stream(params).toArray());
    }
}
