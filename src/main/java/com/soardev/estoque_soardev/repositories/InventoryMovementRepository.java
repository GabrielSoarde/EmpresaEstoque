package com.soardev.estoque_soardev.repositories;

import com.soardev.estoque_soardev.entities.InventoryMovement;
import com.soardev.estoque_soardev.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {

    List<InventoryMovement> findByProduct(Product product);

    List<InventoryMovement>findByMovementDateBetween(
            LocalDateTime startDate,
            LocalDateTime endDate
    );

}
