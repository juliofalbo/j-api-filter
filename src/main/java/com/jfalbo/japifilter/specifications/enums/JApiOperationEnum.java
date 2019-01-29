package com.jfalbo.japifilter.specifications.enums;

public enum JApiOperationEnum {
    EQUAL("="),
    BIGGER(">"),
    SMALLER("<"),
    IN("in"),
    LIKE("%");

    private String value;

    JApiOperationEnum(String value) {
        this.value = value;
    }

    public boolean equalsValue(String othervalue) {
        return value.equals(othervalue);
    }

    public String getValue() {
        return this.value;
    }
}
