package com.telenor.products.utils;

import com.telenor.products.model.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SearchProductTest {

    @Test
    void testBasic() throws IllegalArgException {
        SearchProduct searchProduct = new SearchProduct(ProductType.PHONE.getValue(), 21, 24, "Stockholm", "color", "Black", 3, 10);
        Assertions.assertEquals(searchProduct.getType(), ProductType.PHONE);
        Assertions.assertEquals(21, searchProduct.getMinPrice());
        Assertions.assertEquals(24, searchProduct.getMaxPrice());
        Assertions.assertEquals("Stockholm", searchProduct.getCity());
        Assertions.assertEquals("color", searchProduct.getProperty());
        Assertions.assertEquals("Black", searchProduct.getColor());
        Assertions.assertEquals(3, searchProduct.getGbLimitMin());
        Assertions.assertEquals(10, searchProduct.getGbLimitMax());
    }

    @Test
    void testMinPriceZero() throws IllegalArgException {

        SearchProduct searchProduct = new SearchProduct(ProductType.PHONE.getValue(), 0, 24, "Stockholm", "color", null, 3, 10);

    }

    @Test
    void testMinPriceNegative() {
       Exception exception= Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct(ProductType.PHONE.getValue(), -5, 24, "Stockholm", "color", null, 3, 10));
        Assertions.assertEquals(ProductConstants.MINIMUM_PRICE_GREATER_THAN_ZERO,exception.getMessage());

    }

    @Test
    void testMaxPriceZero() throws IllegalArgException {
        SearchProduct searchProduct = new SearchProduct(ProductType.PHONE.getValue(), 0, 0, "Stockholm", "color", null, 3, 10);

    }

    @Test
    void testMaxPriceNegative() {

        Exception exception=Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct(ProductType.PHONE.getValue(), 23, -6, "Stockholm", "color", null, 3, 10));
        Assertions.assertEquals(ProductConstants.MAXIMUM_PRICE_GREATER_THAN_ZERO,exception.getMessage());
    }

    @Test
    void testMaxPriceMinPriceSmaller() {
        Exception exception=Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct(ProductType.PHONE.getValue(), 22, 20, "Stockholm", "color", null, 3, 10));
        Assertions.assertEquals(ProductConstants.MAXIMUM_PRICE_GREATER_THAN_MINIMUM_PRICE,exception.getMessage());
    }

    @Test
    void testGbLimitMinZero() throws IllegalArgException {
        SearchProduct searchProduct = new SearchProduct(ProductType.PHONE.getValue(), 20, 24, "Stockholm", "color", null, 0, 10);

    }

    @Test
    void testGbLimitMinNegative() {

        Exception exception=Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct(ProductType.PHONE.getValue(), 0, 24, "Stockholm", "color", null, -5, 10));
        Assertions.assertEquals(ProductConstants.GB_LIMIT_MIN_GREATER_THAN_ZERO,exception.getMessage());

    }

    @Test
    void testGbLimitMaxZero() throws IllegalArgException {

        SearchProduct searchProduct = new SearchProduct(ProductType.PHONE.getValue(), 20, 22, "Stockholm", "color", null, 0, 0);

    }

    @Test
    void testGbLimitMaxNegative() {

        Exception exception=Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct(ProductType.PHONE.getValue(), 23, 26, "Stockholm", "color", null, 3, -5));
        Assertions.assertEquals(ProductConstants.GB_LIMIT_MAX_GREATER_THAN_ZERO,exception.getMessage());
    }

    @Test
    void testGbLimitMinPriceSmaller() {
       Exception exception= Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct(ProductType.PHONE.getValue(), 22, 25, "Stockholm", "color", null, 10, 3));
        Assertions.assertEquals(ProductConstants.MAXIMUM_GB_LIMIT_GREATER_THAN_MINIMUM_GB_LIMIT,exception.getMessage());
    }

    @Test
    void test() throws IllegalArgException {
        SearchProduct searchProduct = new SearchProduct(ProductType.PHONE.getValue(), 20, 22, "Stockholm", "color", null, 0, 0);
    }

    @Test
    void testSearchProductTypeEmpty() {
        Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct("", 22, 25, "Stockholm", "color", null, 10, 12));
    }

    @Test
    void testSearchProductTypeNull() throws IllegalArgException {
        SearchProduct searchProduct= new SearchProduct(null, 22, 25, "Stockholm", "color", null, 10, 12);
    }

    @Test
    void testSearchProductType() {
       Exception exception= Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct("", 22, 25, "Stockholm", "color", null, 10, 12));
        Assertions.assertEquals(ProductConstants.VALIDATE_PRODUCT_TYPE,exception.getMessage());
    }

    @Test
    void testSearchProductTypeTest() {
        Exception exception=Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct("Test", 22, 25, "Stockholm", "color", null, 10, 12));
        Assertions.assertEquals(ProductConstants.VALIDATE_PRODUCT_TYPE,exception.getMessage());
    }

    @Test
    void testCityNull() throws IllegalArgException {
        SearchProduct searchProduct= new SearchProduct(null, 22, 25, "Stockholm", "color", null, 10, 12);
    }

    @Test
    void testCityOneChar() {
        Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct("Test", 22, 25, "A", "color", null, 10, 12));
    }
    @Test
    void testCityEmptyCar() {
        Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct("Test", 22, 25, "", "color", null, 10, 12));
    }
    @Test
    void testColor() throws IllegalArgException {
        SearchProduct searchProduct= new SearchProduct(ProductType.SUBSCRIPTION.getValue(), 22, 25, "Stockholm", "color", null, 10, 12);
    }

    @Test
    void testColorOneChar() {
        Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct("Test", 22, 25, "Delhi", "color", "A", 10, 12));
    }
    @Test
    void testColorEmptyChar() {
        Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct("Test", 22, 25, "Delhi", "color", "", 10, 12));
    }

    @Test
    void testPropertyNormal ()throws IllegalArgException {
        SearchProduct searchProduct= new SearchProduct(ProductType.SUBSCRIPTION.getValue(), 22, 25, "Stockholm", "color1", null, 10, 12);
    }

    @Test
    void testPropertyOneChar() {
        Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct("Test", 22, 25, "Delhi", "", "Black", 10, 12));
    }
    @Test
    void testPropertyEmptyChar() {
        Assertions.assertThrows(IllegalArgException.class, () ->
                new SearchProduct("Test", 22, 25, "Delhi", "A", "Black", 10, 12));
    }
}
