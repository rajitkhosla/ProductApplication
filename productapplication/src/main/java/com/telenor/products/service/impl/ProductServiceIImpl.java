package com.telenor.products.service.impl;

import com.telenor.products.model.Product;
import com.telenor.products.model.ProductProperty;
import com.telenor.products.model.ProductType;
import com.telenor.products.model.Property;
import com.telenor.products.repository.ProductRepository;
import com.telenor.products.service.ProductService;
import com.telenor.products.utils.IllegalArgException;
import com.telenor.products.utils.ProductConstants;
import com.telenor.products.utils.SearchProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceIImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceIImpl.class);
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ResourceLoader resourceLoader;

    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProducts(SearchProduct searchProduct) {

        return productRepository.searchProducts(searchProduct.getMinPrice(), searchProduct.getMaxPrice(), searchProduct.getType(), searchProduct.getColor(), searchProduct.getCity(), searchProduct.getGbLimitMax(), searchProduct.getGbLimitMin(), searchProduct.getProperty());

    }

    @Override
    public List<Product> loadData(String fileName) throws IOException {
        List<Product> products = new ArrayList<>();
        Resource resource = resourceLoader.getResource(fileName);
        InputStream inputStream = resource.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        products.addAll(bufferedReader.lines().skip(1).map(line -> {
            String[] p = line.split(ProductConstants.DATA_CSV_REGEX);
            String[] property = p[1].split(ProductConstants.COLON);
            Product product = null;
            try {
                String colorValue = ProductProperty.getInstance(property[0]) == ProductProperty.COLOR ? property[1] : ProductConstants.EMPTY;
                int gbLimitValue = ProductProperty.getInstance(property[0]) == ProductProperty.GB_LIMIT ? Integer.valueOf(property[1]) : 0;
                product = new Product(Double.parseDouble(p[2]), ProductType.getInstance(p[0]), p[3].replaceAll(ProductConstants.REGEX_QUOTES, ProductConstants.EMPTY), new Property(ProductProperty.getInstance(property[0]), colorValue, gbLimitValue));
            } catch (IllegalArgException e) {
                LOGGER.error(ProductConstants.ERROR_WHILE_SAVING, e);
            }
            productRepository.save(product);
            return product;
        }).collect(Collectors.toList()));
        LOGGER.debug(ProductConstants.DATA_CREATED_LOG + products.size());
        return products;
    }
}
