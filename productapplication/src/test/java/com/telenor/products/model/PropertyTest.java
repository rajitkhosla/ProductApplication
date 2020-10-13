package com.telenor.products.model;

import com.telenor.products.utils.IllegalArgException;
import com.telenor.products.utils.ProductConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PropertyTest {

    @Test
    void testBasic() throws IllegalArgException {
        Property property = new Property(ProductProperty.COLOR, "white",3);
        Assertions.assertEquals(ProductProperty.COLOR.getValue(), property.getName());
        Assertions.assertEquals("white", property.getValue());
        Assertions.assertEquals(3, property.getIntValue());
    }

    @Test
    void testPropertyNameNull() {
        Exception exception = Assertions.assertThrows(IllegalArgException.class, () ->
                new Property(null, "white",3));
        Assertions.assertEquals(ProductConstants.PROPERTY_NAME_VALIDATION, exception.getMessage());
    }

    @Test
    void testPropertyValueNull() {
        Exception exception = Assertions.assertThrows(IllegalArgException.class, () ->
                new Property(ProductProperty.COLOR, null,2));
        Assertions.assertEquals(ProductConstants.PROPERTY_VALUE_VALIDATION, exception.getMessage());
    }

    @Test
    void testPropertyValueIntValue() {
        Exception exception = Assertions.assertThrows(IllegalArgException.class, () ->
                new Property(ProductProperty.COLOR, "1",-5));
        Assertions.assertEquals(ProductConstants.PROPERTY_INT_VALUE, exception.getMessage());
    }

    @Test
    void testPropertyToString() throws IllegalArgException {
        Property property = new Property(ProductProperty.COLOR, "white",3);
        String verifyString="Property{" +
                "name='" + ProductProperty.COLOR.getValue() + '\'' +
                ", value='" + "white" + '\'' +
                ", intValue=" + 3 +
                '}';
        Assertions.assertEquals(verifyString, property.toString());
    }
}
