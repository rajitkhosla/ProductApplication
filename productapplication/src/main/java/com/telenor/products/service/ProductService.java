package com.telenor.products.service;

import com.telenor.products.model.Product;
import com.telenor.products.utils.IllegalArgException;
import com.telenor.products.utils.SearchProduct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {
    List<Product> searchProducts(SearchProduct searchProduct);

    List<Product> loadData(String fileName) throws IOException;
}
