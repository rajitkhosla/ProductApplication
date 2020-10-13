package com.telenor.products.model;

import java.util.HashMap;
import java.util.Map;

public enum ProductType {
    SUBSCRIPTION("subscription"), PHONE("phone");

    private static final Map<String, ProductType> MAP = new HashMap<>();

    static {
        for (ProductType value : values()) {
            MAP.put(value.name().toLowerCase(), value);
            MAP.put(value.getValue().toLowerCase(), value);
        }
    }

    public final String label;

    private ProductType(String label) {
        this.label = label;
    }

    public static ProductType getInstance(String name) {
        return name == null ? null : MAP.get(name.trim().toLowerCase());
    }

    public String getValue() {
        return label;
    }
}
