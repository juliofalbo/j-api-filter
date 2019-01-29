package com.jfalbo.japifilter.specifications.domain;

import com.jfalbo.japifilter.specifications.enums.JApiOperationEnum;

public class JApiFilter {

    private String field;

    private FieldIn fieldIn;

    private JApiOperationEnum operation;

    private Object value;

    public JApiFilter(String field, Object value, JApiOperationEnum operation) {
        this.field = field;
        this.value = value;
        this.operation = operation;
    }

    public JApiFilter(FieldIn fieldIn, Object value, JApiOperationEnum operation) {
        this.fieldIn = fieldIn;
        this.value = value;
        this.operation = operation;
    }

    public String getField() {
        return field;
    }

    public JApiOperationEnum getOperation() {
        return operation;
    }

    public Object getValue() {
        return value;
    }

    public FieldIn getFieldIn() {
        return fieldIn;
    }

}
