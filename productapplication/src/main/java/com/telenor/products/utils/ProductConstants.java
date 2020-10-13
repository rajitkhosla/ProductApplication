package com.telenor.products.utils;

public final class ProductConstants {

    public static final String PRICE_GREATER_THAN_ZERO = "Product price should be greater than zero";
    public static final String PRODUCT_TYPE_MINIMUM_SIZE = "Product type cannot be null";
    public static final String STORE_ADDRESS_MINIMUM_SIZE = "Store Address should be minimum 5 digits";
    public static final String PROPERTY_NOT_NULL = "Property cannot be null";
    public static final String DATA_CSV_REGEX = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    public static final String MINIMUM_PRICE_GREATER_THAN_ZERO = "Minimum Price should be greater than or equal 0";
    public static final String MAXIMUM_PRICE_GREATER_THAN_ZERO = "Maximum Price should be greater than or equal 0";
    public static final String MAXIMUM_PRICE_GREATER_THAN_MINIMUM_PRICE = "Maximum Price should be greater than Minimum Price";
    public static final String GB_LIMIT_MIN_GREATER_THAN_ZERO = "Minimum GB Limit should be greater than or equal 0";
    public static final String GB_LIMIT_MAX_GREATER_THAN_ZERO = "Maximum GB Limit should be greater than or equal 0";
    public static final String MAXIMUM_GB_LIMIT_GREATER_THAN_MINIMUM_GB_LIMIT = "Maximum GB Limit should be greater than Minimum GB Limit";
    public static final String CITY_NAME_VALIDATION = "Please enter valid city Name";
    public static final String PROPERTY_NAME_VALIDATION = "Please enter valid property Name";
    public static final String PROPERTY_VALUE_VALIDATION = "Please enter valid Color";
    public static final String PROPERTY_INT_VALUE = "Please enter valid value";
    public static final String VALIDATE_PRODUCT_TYPE = "Please give valid product type";

    //Logs
    public static final String DATA_CSV = "classpath:data.csv";
    public static final String COLON = ":";
    public static final String REGEX_QUOTES = "\"";
    public static final String EMPTY = "";
    public static final String DATA_CREATED_LOG = "Total data loaded count-";
    public static final String REQUEST_RECIEVED_IN_SEARCH_PRODUCT = "Request Recieved in Search Product-";
    public static final String ERROR_WHILE_SAVING = "Error While saving the data-";

    private ProductConstants() {

    }
}

