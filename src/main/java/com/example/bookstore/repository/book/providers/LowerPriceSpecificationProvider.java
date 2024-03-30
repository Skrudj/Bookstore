package com.example.bookstore.repository.book.providers;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.SpecificationProvider;
import com.example.bookstore.repository.book.BookParameters;
import java.math.BigDecimal;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Getter
@Component
public class LowerPriceSpecificationProvider implements SpecificationProvider<Book> {
    private final String key = BookParameters.BOOK_LOWER_PRICE.getKey();

    @Override
    public Specification<Book> getSpecification(String[] params) {
        if (params.length == 1) {
            return (root, query, criteriaBuilder)
                    -> criteriaBuilder.greaterThanOrEqualTo(root.get(
                            BookParameters.PRICE.getKey()),
                    BigDecimal.valueOf(Long.parseLong(params[0]))
            );
        }

        return Specification.where(null);
    }
}
