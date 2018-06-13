package com.jfalbo.japifilter.specifications;

import com.jfalbo.japifilter.specifications.domain.JApiFilter;
import com.jfalbo.japifilter.specifications.enums.JApiOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
@Builder
public class JApiDefaultSpecification<T> implements Specification<T> {

    private JApiFilter filter;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (filter.getOperation().equals(JApiOperationEnum.BIGGER)) {
            return builder.greaterThanOrEqualTo(
                    root.<String>get(filter.getField()), filter.getValue().toString());
        } else if (filter.getOperation().equals(JApiOperationEnum.SMALLER)) {
            return builder.lessThanOrEqualTo(
                    root.<String>get(filter.getField()), filter.getValue().toString());
        } else if (filter.getOperation().equals(JApiOperationEnum.EQUAL)) {
            return builder.equal(root.get(filter.getField()), filter.getValue());
        } else if (filter.getOperation().equals(JApiOperationEnum.LIKE)) {
            return builder.like(
                    root.<String>get(filter.getField()), "%" + filter.getValue() + "%");
        }
        return null;
    }
}
