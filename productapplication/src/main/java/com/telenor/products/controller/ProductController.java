package com.telenor.products.controller;

import com.telenor.products.service.ProductService;
import com.telenor.products.utils.ProductConstants;
import com.telenor.products.utils.SearchProduct;
import com.telenor.products.wrapper.ProductWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class ProductController implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @GetMapping(value = "/product", consumes = "application/json")
    public ProductWrapper searchProduct(@RequestBody SearchProduct searchProduct) {
        LOGGER.debug(ProductConstants.REQUEST_RECIEVED_IN_SEARCH_PRODUCT, searchProduct);
        return new ProductWrapper(productService.searchProducts(searchProduct));
    }
}
