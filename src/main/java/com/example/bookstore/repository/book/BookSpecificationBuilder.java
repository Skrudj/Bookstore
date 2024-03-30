package com.example.bookstore.repository.book;

import com.example.bookstore.dto.request.BookSearchParameters;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.SpecificationBuilder;
import com.example.bookstore.repository.SpecificationProviderManager;
import com.example.bookstore.util.ObjectUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book, BookSearchParameters> {
    private final SpecificationProviderManager<Book> specificationProviderManager;
    private final ObjectUtil objectUtil;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> specification = Specification.where(null);
        Map<String, Object> fieldValues = objectUtil.getFieldValues(searchParameters);

        for (Map.Entry<String, Object> entry: fieldValues.entrySet()) {
            Object value = entry.getValue();

            if (value != null) {
                String[] specificationInput;

                if (value instanceof List) {
                    specificationInput = (String[]) ((ArrayList<?>) value)
                            .stream()
                            .map(Object::toString)
                            .toArray();
                } else {
                    specificationInput = new String[]{value.toString()};
                }

                specification = specification.and(
                        specificationProviderManager
                                .getSpecificationProvider(entry.getKey())
                                .getSpecification(specificationInput)
                );
            }
        }

        return specification;
    }
}
