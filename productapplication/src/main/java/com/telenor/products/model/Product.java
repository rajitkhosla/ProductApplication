package com.telenor.products.model;

import com.telenor.products.utils.ProductConstants;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    @Min(value = 1, message = ProductConstants.PRICE_GREATER_THAN_ZERO)
    @NotNull(message = ProductConstants.PRICE_GREATER_THAN_ZERO)
    private Double price;

    @Column
    @NotNull(message = ProductConstants.PRODUCT_TYPE_MINIMUM_SIZE)
    private ProductType type;

    @Column
    @NotNull(message = ProductConstants.STORE_ADDRESS_MINIMUM_SIZE)
    @Size(min = 5, message = ProductConstants.STORE_ADDRESS_MINIMUM_SIZE)
    private String storeAddress;

    @Embedded
    @NotNull(message = ProductConstants.PROPERTY_NOT_NULL)
    private Property property;


    public Product(Double price, ProductType type, String storeAddress, Property property) {
        this.price = price;
        this.type = type;
        this.storeAddress = storeAddress;
        this.property = property;
    }

    private Product() {
    }

    public String getProperty() {
        return property.getName() + ProductConstants.COLON + (StringUtils.isEmpty(property.getValue()) ? property.getIntValue() : property.getValue());
    }

    @PrePersist
    public void prePersistValidate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        factory.getValidator().validate(this);
    }


    public Double getPrice() {
        return price;
    }

    public String getType() {
        return type.getValue();
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(price, product.price) &&
                type == product.type &&
                Objects.equals(storeAddress, product.storeAddress) &&
                Objects.equals(property, product.property);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, type, storeAddress, property);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", type=" + type +
                ", storeAddress='" + storeAddress + '\'' +
                ", property=" + property +
                '}';
    }
}

