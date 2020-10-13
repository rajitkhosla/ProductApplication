package com.telenor.products.wrapper;


import com.telenor.products.model.Product;

import java.util.List;

public class ProductWrapper {
    private final List<Product> data;

    public ProductWrapper(List<Product> products) {
        this.data = products;
    }

    public List<Product> getData() {
        return data;
    }
}
