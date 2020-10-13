package com.telenor.products.wrapper;

import com.telenor.products.model.Product;
import com.telenor.products.model.ProductProperty;
import com.telenor.products.model.ProductType;
import com.telenor.products.model.Property;
import com.telenor.products.utils.IllegalArgException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class ProductWrapperTest {
    @Test
    void testProductWrapperBasics() throws IllegalArgException {
        Product product = new Product(23.0, ProductType.PHONE, "123,Stockholm", new Property(ProductProperty.COLOR, "white",3));
        ProductWrapper productWrapper = new ProductWrapper(Collections.singletonList(product));
        Assertions.assertEquals(1, productWrapper.getData().size());
        Assertions.assertEquals(product, productWrapper.getData().get(0));
    }
    @Test
    void testProductWrapperEmpty() {

        ProductWrapper productWrapper = new ProductWrapper(Collections.emptyList());
        Assertions.assertEquals(0, productWrapper.getData().size());

    }
    @Test
    void testProductWrapperMoreThan1() throws IllegalArgException {
        Product product1 = new Product(23.0, ProductType.PHONE, "123,Stockholm", new Property(ProductProperty.COLOR, "white",3));
        Product product2 = new Product(23.0, ProductType.SUBSCRIPTION, "123,Stockholm", new Property(ProductProperty.GB_LIMIT, "12",3));
        ProductWrapper productWrapper = new ProductWrapper(Arrays.asList(product1,product2));
        Assertions.assertEquals(2, productWrapper.getData().size());
        Assertions.assertEquals(product1, productWrapper.getData().get(0));
        Assertions.assertEquals(product2, productWrapper.getData().get(1));
    }
}
