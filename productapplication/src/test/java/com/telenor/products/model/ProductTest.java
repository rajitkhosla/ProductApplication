package com.telenor.products.model;

import com.telenor.products.utils.IllegalArgException;
import com.telenor.products.utils.ProductConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class ProductTest {
    private Validator validator;

    @BeforeEach
    public void setUp() throws IllegalArgException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void testBasic() throws IllegalArgException {
        Product product = new Product(23.0, ProductType.PHONE, "123,Stockholm", new Property(ProductProperty.COLOR, "white",4));
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        Assertions.assertEquals(0, violations.size());
        Assertions.assertEquals(23.0,product.getPrice());
        Assertions.assertEquals(ProductType.PHONE.getValue(),product.getType());
        Assertions.assertEquals("123,Stockholm",product.getStoreAddress());
        Assertions.assertEquals("color:white",product.getProperty());

    }

    @Test
    void testPriceZero() throws IllegalArgException {
        Product product1 = new Product(0.0, ProductType.PHONE, "123,Stockholm", new Property(ProductProperty.COLOR, "white",3));
        Set<ConstraintViolation<Product>> violations = validator.validate(product1);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(ProductConstants.PRICE_GREATER_THAN_ZERO, violationList.get(0));
    }

    @Test
    void testPriceMinus() throws IllegalArgException {
        Product product1 = new Product(-5.0, ProductType.PHONE, "123,Stockholm", new Property(ProductProperty.COLOR, "white",3));
        Set<ConstraintViolation<Product>> violations = validator.validate(product1);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(ProductConstants.PRICE_GREATER_THAN_ZERO, violationList.get(0));
    }

    @Test
    void testPriceNull() throws IllegalArgException {
        Product product1 = new Product(null, ProductType.PHONE, "123,Stockholm", new Property(ProductProperty.COLOR, "white",4));
        Set<ConstraintViolation<Product>> violations = validator.validate(product1);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(ProductConstants.PRICE_GREATER_THAN_ZERO, violationList.get(0));
    }

    @Test
    void testTypeNull() throws IllegalArgException {
        Product product1 = new Product(5.0, null, "123,Stockholm", new Property(ProductProperty.COLOR, "white",3));
        Set<ConstraintViolation<Product>> violations = validator.validate(product1);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(ProductConstants.PRODUCT_TYPE_MINIMUM_SIZE, violationList.get(0));
    }
    @Test
    void testAddressNull() throws IllegalArgException {
        Product product1 = new Product(5.0, ProductType.PHONE, null, new Property(ProductProperty.COLOR, "white",3));
        Set<ConstraintViolation<Product>> violations = validator.validate(product1);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(ProductConstants.STORE_ADDRESS_MINIMUM_SIZE, violationList.get(0));
    }
    @Test
    void testAddress1Char() throws IllegalArgException {
        Product product1 = new Product(5.0, ProductType.PHONE, "1", new Property(ProductProperty.COLOR, "white",3));
        Set<ConstraintViolation<Product>> violations = validator.validate(product1);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(ProductConstants.STORE_ADDRESS_MINIMUM_SIZE, violationList.get(0));
    }
    @Test
    void testAddress3Char() throws IllegalArgException {
        Product product1 = new Product(5.0, ProductType.PHONE, "del", new Property(ProductProperty.COLOR, "white",3));
        Set<ConstraintViolation<Product>> violations = validator.validate(product1);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(ProductConstants.STORE_ADDRESS_MINIMUM_SIZE, violationList.get(0));
    }
    @Test
    void testPropertyNull() throws IllegalArgException {
        Product product1 = new Product(5.0, ProductType.PHONE, "123,Stockholm", null);
        Set<ConstraintViolation<Product>> violations = validator.validate(product1);
        Assertions.assertEquals(1, violations.size());
        List<String> violationList = violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList());
        Assertions.assertEquals(ProductConstants.PROPERTY_NOT_NULL, violationList.get(0));
    }


}
