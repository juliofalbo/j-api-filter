package com.jfalbo.japifilter.example.builder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

/**
 * @author s2it_jsilveira
 */
public class ExampleBuilder<T> {

    private HashMap<String, Object> filters = new HashMap<>();

    private Class<T> type;

    public ExampleBuilder(Class<T> type) {
        this.type = type;
    }

    public ExampleBuilder addFilter(String key, Object value) {
        this.filters.put(key, value);
        return this;
    }

    public Example<T> build() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase();
        return build(matcher);
    }

    public Example<T> build(ExampleMatcher matcher) {
        Example<T> example = null;

        try {
            final T obj = type.getConstructor().newInstance();

            filters.forEach((key, value) -> {
                try {
                    final Field declaredField = type.getDeclaredField(key);
                    declaredField.setAccessible(true);
                    declaredField.set(obj, value);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });

            example = Example.of(obj, matcher);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return example;
    }

}