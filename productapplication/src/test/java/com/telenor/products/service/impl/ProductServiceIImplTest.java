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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductServiceIImplTest {
    @Autowired
    ProductService productService;
    @Autowired
    ResourceLoader resourceLoader;

    SearchProduct searchProduct;
    Product product;
    @MockBean
    private ProductRepository productRepository;

    @BeforeEach()
    public void setUp() throws IllegalArgException {
        product = new Product(5.0, ProductType.PHONE, "1", new Property(ProductProperty.COLOR, "white",3));
        searchProduct = new SearchProduct(ProductType.PHONE.getValue(), 21, 24, "Stockholm", "color", "Black", 3, 10);

    }

    @Test
    void searchProductsBasics() {
        Mockito.when(productRepository.searchProducts(searchProduct.getMinPrice(), searchProduct.getMaxPrice(), searchProduct.getType(), searchProduct.getColor(), searchProduct.getCity(), searchProduct.getGbLimitMax(), searchProduct.getGbLimitMin(), searchProduct.getProperty()))
                .thenReturn(Collections.singletonList(product));
        List<Product> productList = productService.searchProducts(searchProduct);
        Assertions.assertEquals(1, productList.size());
        Assertions.assertEquals(product, productList.get(0));
    }

    @Test
    void searchProductsEmpty() {
        Mockito.when(productRepository.searchProducts(searchProduct.getMinPrice(), searchProduct.getMaxPrice(), searchProduct.getType(), searchProduct.getColor(), searchProduct.getCity(), searchProduct.getGbLimitMax(), searchProduct.getGbLimitMin(), searchProduct.getProperty()))
                .thenReturn(Collections.emptyList());
        List<Product> productList = productService.searchProducts(searchProduct);
        Assertions.assertEquals(0, productList.size());
    }

    @Test
    void loadData() throws IOException {
        List<Product> products = productService.loadData(ProductConstants.DATA_CSV);
        List<Product> readProducts = findProducts();
        Assertions.assertEquals(readProducts.size(), products.size());
        Assertions.assertEquals(readProducts.get(2), products.get(2));
    }

    @Test
    void loadDataFileNotPresent() throws IOException {
        Assertions.assertThrows(FileNotFoundException.class, () ->
                productService.loadData("test121111111111111111113"));
    }

    private List<Product> findProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        Resource resource = resourceLoader.getResource(ProductConstants.DATA_CSV);
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

            }
            productRepository.save(product);
            return product;
        }).collect(Collectors.toList()));
        return products;
    }

    @TestConfiguration
    static class CarRentalUserConfiguration {

        @Bean
        public ProductService productService() {
            return new ProductServiceIImpl();
        }

    }


}
