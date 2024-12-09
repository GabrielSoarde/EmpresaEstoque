package com.soardev.estoque_soardev.repositories;

import com.soardev.estoque_soardev.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContainingIgnoreCase(String productName);

    @Query("SELECT p FROM Product p WHERE p.inventoryQuantity < 5")
    List<Product> findProductLowInventory();

    List<Product> findByCategory(Product.ProductCategory category);

}
