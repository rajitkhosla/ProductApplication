package com.telenor.products.model;

import com.telenor.products.utils.IllegalArgException;
import com.telenor.products.utils.ProductConstants;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Property {
    private String name;
    private String value;
    private int intValue;

    public Property(ProductProperty productProperty, String value,int intValue) throws IllegalArgException {
        validateProperty(productProperty);
        validateValue(value);
        validateIntValue(intValue);
        this.name = productProperty.getValue();
        this.value = value;
        this.intValue=intValue;
    }

    private Property() {

    }

    private String validateProperty(ProductProperty productProperty) throws IllegalArgException {
        if (productProperty == null) {
            throw new IllegalArgException(ProductConstants.PROPERTY_NAME_VALIDATION);
        }
        return value;
    }

    private void validateValue(String value) throws IllegalArgException {
        if (value == null) {
            throw new IllegalArgException(ProductConstants.PROPERTY_VALUE_VALIDATION);
        }
    }
    private void validateIntValue(int intValue) throws IllegalArgException {
        if (intValue < 0) {
            throw new IllegalArgException(ProductConstants.PROPERTY_INT_VALUE);
        }
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getIntValue() {
        return intValue;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", intValue=" + intValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return intValue == property.intValue &&
                Objects.equals(name, property.name) &&
                Objects.equals(value, property.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, intValue);
    }
}
