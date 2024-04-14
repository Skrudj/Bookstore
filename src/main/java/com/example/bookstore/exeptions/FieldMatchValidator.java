package com.example.bookstore.exeptions;

import com.example.bookstore.exeptions.annotations.FieldMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        boolean valid = true;

        try {
            Object firstField = BeanUtils.getProperty(o, firstFieldName);
            Object secondField = BeanUtils.getProperty(o, secondFieldName);

            valid = firstField == null && secondField == null || firstField != null && firstField.equals(secondField);
        } catch (Exception e) {
            throw new RuntimeException("Can't access fields of object: " + o.toString(), e);
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
