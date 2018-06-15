package com.jfalbo.japifilter.specifications.domain;

import com.jfalbo.japifilter.specifications.enums.JApiOperationEnum;

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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public JApiOperationEnum getOperation() {
        return operation;
    }

    public void setOperation(JApiOperationEnum operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
