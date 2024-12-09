package com.soardev.estoque_soardev.services;

import com.soardev.estoque_soardev.entities.InventoryMovement;
import com.soardev.estoque_soardev.entities.Product;
import com.soardev.estoque_soardev.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryMovement inventoryMovement;

    @Transactional
    public Product inventoryEntry(Long productId, Integer quantity){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        product.setInventoryQuantity(product.getInventoryQuantity() + quantity);

        InventoryMovement mov = new InventoryMovement();
        mov.setProduct(product);
        mov.setQuantity(quantity);
        mov.setMovType(InventoryMovement.MovType.ENTRADA);

        return productRepository.save(product);
    }

    @Transactional
    public Product inventoryExit(Long productId, Integer quantity){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        if(product.getInventoryQuantity() < quantity){
            throw new RuntimeException("Estoque insuficiente");
        }
        product.setInventoryQuantity(product.getInventoryQuantity() - quantity);

        InventoryMovement mov = new InventoryMovement();
        mov.setProduct(product);
        mov.setQuantity(quantity);
        mov.setMovType(InventoryMovement.MovType.SAIDA);

        return productRepository.save(product);
    }
}
