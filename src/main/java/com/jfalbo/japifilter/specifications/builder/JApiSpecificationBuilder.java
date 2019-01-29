package com.jfalbo.japifilter.specifications.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.jfalbo.japifilter.specifications.JApiDefaultSpecification;
import com.jfalbo.japifilter.specifications.domain.FieldIn;
import com.jfalbo.japifilter.specifications.domain.JApiFilter;
import com.jfalbo.japifilter.specifications.enums.JApiOperationEnum;
import org.springframework.data.jpa.domain.Specification;

public class JApiSpecificationBuilder {

    private List<JApiFilter> filters = new ArrayList<>();

    public static JApiSpecificationBuilder init() {
        return new JApiSpecificationBuilder();
    }

    public JApiSpecificationBuilder withEqualFilter(String name, Object value) {
        addFilterIfValueIsNotNull(name, value, JApiOperationEnum.EQUAL);
        return this;
    }

    public JApiSpecificationBuilder withBiggerFilter(String name, Object value) {
        addFilterIfValueIsNotNull(name, value, JApiOperationEnum.BIGGER);
        return this;
    }

    public JApiSpecificationBuilder withSmallerFilter(String name, Object value) {
        addFilterIfValueIsNotNull(name, value, JApiOperationEnum.SMALLER);
        return this;
    }

    public JApiSpecificationBuilder withLikeFilter(String name, Object value) {
        addFilterIfValueIsNotNull(name, value, JApiOperationEnum.LIKE);
        return this;
    }

    public JApiSpecificationBuilder withInFilter(FieldIn inField, Object value) {
        addFilterIfValueIsNotNull(inField, value, JApiOperationEnum.IN);
        return this;
    }

    public JApiSpecificationBuilder withCustomFilter(String name, Object value, JApiOperationEnum operation) {
        if (value != null) {
            JApiFilter field = new JApiFilter(name, value, operation);
            this.filters.add(field);
        }
        return this;
    }

    public JApiSpecificationBuilder createFiltersByEntity(final Object object, JApiOperationEnum operationSpecificationEnum) {

        Arrays.asList(object.getClass().getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            try {
                addFilterIfValueIsNotNull(field.getName(), field.get(object), operationSpecificationEnum);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return this;
    }

    public List<JApiFilter> buildList() {
        return this.filters;
    }

    public <T> Specification<T> buildSpec() {
        if (filters.isEmpty()) {
            return Specification.where(null);
        }
        AtomicReference<Specification<T>> searchSpec = new AtomicReference<>(Specification.where(new JApiDefaultSpecification<T>(filters.get(0))));
        filters.remove(filters.get(0));

        filters.forEach(filter -> searchSpec.set(searchSpec.get().and(new JApiDefaultSpecification<T>(filter))));
        return searchSpec.get();
    }

    private void addFilterIfValueIsNotNull(String field, Object value, JApiOperationEnum operation) {
        if (value != null) {
            JApiFilter fieldSpec = new JApiFilter(field, value, operation);
            this.filters.add(fieldSpec);
        }
    }

    private void addFilterIfValueIsNotNull(FieldIn fieldIn, Object value, JApiOperationEnum operation) {
        if (value != null) {
            JApiFilter fieldSpec = new JApiFilter(fieldIn, value, operation);
            this.filters.add(fieldSpec);
        }
    }

}
