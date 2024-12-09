package com.soardev.estoque_soardev.services;

import com.soardev.estoque_soardev.entities.Product;
import com.soardev.estoque_soardev.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product creatProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product productUpdated) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        existingProduct.setProductName(productUpdated.getProductName());
        existingProduct.setDescription(productUpdated.getDescription());
        existingProduct.setPrice(productUpdated.getPrice());

        return productRepository.save(existingProduct);
    }

    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    public List<Product> listProductLowInventory() {
        return productRepository.findProductLowInventory();
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
