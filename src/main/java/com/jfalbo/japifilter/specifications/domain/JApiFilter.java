package com.jfalbo.japifilter.specifications.domain;

import com.jfalbo.japifilter.specifications.enums.JApiOperationEnum;
import lombok.Data;

@Data
public class JApiFilter {

    private String field;
    private JApiOperationEnum operation;
    private Object value;

    public JApiFilter(String field) {
        this.field = field;
    }

    public JApiFilter(String field, Object value, JApiOperationEnum operation) {
        this.field = field;
        this.value = value;
        this.operation = operation;
    }

}
