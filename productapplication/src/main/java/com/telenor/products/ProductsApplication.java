package com.telenor.products;

import com.telenor.products.model.Product;
import com.telenor.products.repository.ProductRepository;
import com.telenor.products.service.ProductService;
import com.telenor.products.utils.ProductConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ProductsApplication implements CommandLineRunner {
    @Autowired
    ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productService.loadData(ProductConstants.DATA_CSV);
    }
}
