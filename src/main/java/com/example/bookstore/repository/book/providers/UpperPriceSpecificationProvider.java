package com.example.bookstore.repository.book.providers;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.SpecificationProvider;
import com.example.bookstore.repository.book.BookParameters;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Getter
@Component
public class UpperPriceSpecificationProvider implements SpecificationProvider<Book> {
    private final String key = BookParameters.BOOK_UPPER_PRICE.getKey();

    @Override
    public Specification<Book> getSpecification(String[] params) {
        if (params.length == 1) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.lessThanOrEqualTo(root.get(
                            BookParameters.PRICE.getKey()),
                    params[0]
            );
        }

        return Specification.where(null);
    }
}
