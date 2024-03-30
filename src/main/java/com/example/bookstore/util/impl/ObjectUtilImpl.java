package com.example.bookstore.util.impl;

import com.example.bookstore.util.ObjectUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ObjectUtilImpl implements ObjectUtil {

    @Override
    public Map<String, Object> getFieldValues(Object obj) {
        Map<String, Object> fieldValues = new HashMap<>();
        Class<?> objClass = obj.getClass();

        Field[] fields = objClass.getDeclaredFields();

        for (Field field: fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(obj);
                fieldValues.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cant access field of object " + obj.toString(), e);
            }
        }

        return fieldValues;
    }
}
