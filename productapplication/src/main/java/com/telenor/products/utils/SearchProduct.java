package com.telenor.products.utils;

import com.telenor.products.model.ProductType;
import org.springframework.util.StringUtils;

public class SearchProduct {

    private ProductType type;
    private double min_Price;
    private double max_Price;
    private String city;
    private String color;
    private int gbLimitMin;
    private int gbLimitMax;
    private String property;

    public SearchProduct(String type, double min_Price, double max_Price, String city, String property, String color, int gbLimitMin, int gbLimitMax) throws IllegalArgException {
        validatePrice(min_Price, max_Price);
        validateGbLimit(gbLimitMin, gbLimitMax);
        this.type = validateProductType(type);
        this.min_Price = min_Price;
        this.max_Price = max_Price;
        this.city = validateCity(city);
        this.color = validateColor(color);
        this.gbLimitMin = gbLimitMin;
        this.gbLimitMax = gbLimitMax;
        this.property = validateProperty(property);
    }

    private void validateGbLimit(int gbLimitMin, int gbLimitMax) throws IllegalArgException {
        if (gbLimitMin < 0) {
            throw new IllegalArgException(ProductConstants.GB_LIMIT_MIN_GREATER_THAN_ZERO);
        }
        if (gbLimitMax < 0) {
            throw new IllegalArgException(ProductConstants.GB_LIMIT_MAX_GREATER_THAN_ZERO);
        }
        if (gbLimitMax > 0.0 && gbLimitMin>0.0 && gbLimitMin >= gbLimitMax) {
            throw new IllegalArgException(ProductConstants.MAXIMUM_GB_LIMIT_GREATER_THAN_MINIMUM_GB_LIMIT);
        }
    }

    private String validateCity(String city) throws IllegalArgException {
        if (city != null && city.length() <= 1) {
            throw new IllegalArgException(ProductConstants.CITY_NAME_VALIDATION);
        }
        return city;
    }

    private String validateColor(String color) throws IllegalArgException {
        if (color != null && color.length() <= 1) {
            throw new IllegalArgException(ProductConstants.PROPERTY_VALUE_VALIDATION);
        }
        return color;
    }

    private String validateProperty(String property) throws IllegalArgException {
        if (property != null && property.length() <= 1) {
            throw new IllegalArgException(ProductConstants.PROPERTY_NAME_VALIDATION);
        }
        return property;
    }

    private void validatePrice(double min_price, double max_price) throws IllegalArgException {
        if (min_price < 0) {
            throw new IllegalArgException(ProductConstants.MINIMUM_PRICE_GREATER_THAN_ZERO);
        }
        if (max_price < 0) {
            throw new IllegalArgException(ProductConstants.MAXIMUM_PRICE_GREATER_THAN_ZERO);
        }
        if (min_price > 0.0 && max_price >0.0 && min_price >= max_price) {
            throw new IllegalArgException(ProductConstants.MAXIMUM_PRICE_GREATER_THAN_MINIMUM_PRICE);
        }
    }


    private ProductType validateProductType(String type) throws IllegalArgException {
        ProductType productType = StringUtils.isEmpty(type) ? null : ProductType.getInstance(type);
        if (productType == null && type != null)
            throw new IllegalArgException(ProductConstants.VALIDATE_PRODUCT_TYPE);
        return productType;
    }

    public ProductType getType() {
        return type;
    }

    public double getMinPrice() {
        return min_Price;
    }

    public double getMaxPrice() {
        return max_Price;
    }

    public String getCity() {
        return city;
    }

    public String getColor() {
        return color;
    }

    public int getGbLimitMin() {
        return gbLimitMin;
    }

    public int getGbLimitMax() {
        return gbLimitMax;
    }

    public String getProperty() {
        return property;
    }
}


