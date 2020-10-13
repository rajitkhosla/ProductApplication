package com.telenor.products.repository;

import com.telenor.products.model.Product;
import com.telenor.products.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select a from Product a where (((:minPrice is 0.0 or a.price >= :minPrice) AND (:maxPrice is 0.0 or a.price <= :maxPrice) AND (:type is null or a.type = :type) AND (:propertyName is null or a.property.name = :propertyName) AND (:color is null or a.property.value = :color) AND (:city is null or a.storeAddress LIKE %:city%) AND (:propertyGBLimitMin is 0 or a.property.intValue >= :propertyGBLimitMin) AND (:propertyGBLimitMax is 0 or (a.property.intValue > 0 AND a.property.intValue <= :propertyGBLimitMax))))")
    List<Product> searchProducts(
            @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, @Param("type") ProductType type, @Param("color") String color, @Param("city") String city, @Param("propertyGBLimitMax") int propertyGBLimitMax, @Param("propertyGBLimitMin") int propertyGBLimitMin, @Param("propertyName") String property);

}
