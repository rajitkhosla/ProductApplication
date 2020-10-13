package com.telenor.products.model;

import java.util.HashMap;
import java.util.Map;

public enum ProductProperty {
    COLOR("color"), GB_LIMIT("gb_limit");

    private static final Map<String, ProductProperty> MAP = new HashMap<>();

    static {
        for (ProductProperty value : values()) {
            MAP.put(value.name().toLowerCase(), value);
            MAP.put(value.getValue().toLowerCase(), value);
        }
    }

    public final String label;

    private ProductProperty(String label) {
        this.label = label;
    }

    public static ProductProperty getInstance(String name) {
        return name == null ? null : MAP.get(name.trim().toLowerCase());
    }

    public String getValue() {
        return label;
    }
}
