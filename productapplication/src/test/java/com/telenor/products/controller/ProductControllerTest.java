package com.telenor.products.controller;

import com.telenor.products.model.Product;
import com.telenor.products.model.ProductProperty;
import com.telenor.products.model.ProductType;
import com.telenor.products.model.Property;
import com.telenor.products.service.ProductService;
import com.telenor.products.utils.IllegalArgException;
import com.telenor.products.utils.SearchProduct;
import com.telenor.products.wrapper.ProductWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    SearchProduct searchProduct;

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @BeforeEach
    public void testBeforeEach() throws IllegalArgException {
        searchProduct = new SearchProduct(ProductType.PHONE.getValue(), 21, 24, "Stockholm", "color", null, 0, 0);
    }

    @Test
    public void testProductControllerSingleTest() throws IllegalArgException {

        Product product = new Product(23.0, ProductType.PHONE, "123,Stockholm", new Property(ProductProperty.COLOR, "white",2));

        Mockito.when(productService.searchProducts(searchProduct)).thenReturn(Collections.singletonList(product));

        ProductWrapper productWrapper = productController.searchProduct(searchProduct);

        Assertions.assertEquals(product, productWrapper.getData().get(0));
        Assertions.assertEquals(1, productWrapper.getData().size());

    }

    @Test
    public void testProductControllerMultiTest() throws IllegalArgException {

        Product product1 = new Product(23.0, ProductType.PHONE, "123,Stockholm", new Property(ProductProperty.COLOR, "white",2));
        Product product2 = new Product(25.0, ProductType.SUBSCRIPTION, "456,Stockholm", new Property(ProductProperty.GB_LIMIT, "14",2));

        Mockito.when(productService.searchProducts(searchProduct)).thenReturn(Arrays.asList(product1, product2));

        ProductWrapper productWrapper = productController.searchProduct(searchProduct);

        Assertions.assertEquals(2, productWrapper.getData().size());
        Assertions.assertEquals(product1, productWrapper.getData().get(0));
        Assertions.assertEquals(product2, productWrapper.getData().get(1));

    }

    @Test
    public void testProductControllerEmptyList() {

        Mockito.when(productService.searchProducts(searchProduct)).thenReturn(Collections.emptyList());

        ProductWrapper productWrapper = productController.searchProduct(searchProduct);

        Assertions.assertEquals(0, productWrapper.getData().size());

    }

}
