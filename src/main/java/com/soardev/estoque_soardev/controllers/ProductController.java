package com.soardev.estoque_soardev.controllers;

import com.soardev.estoque_soardev.entities.LoginRequest;
import com.soardev.estoque_soardev.entities.Product;
import com.soardev.estoque_soardev.entities.User;
import com.soardev.estoque_soardev.services.ProductService;
import com.soardev.estoque_soardev.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/produtos")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        return ResponseEntity.ok(productService.creatProduct(product));
    }

    @GetMapping("/estoque")
    public ResponseEntity<List<Product>>  listProducts(){
        return ResponseEntity.ok(productService.listProduct());
    }

    @GetMapping("/estoque-baixo")
    public ResponseEntity<List<Product>> listLowStockProduct(){
        return ResponseEntity.ok(productService.listProductLowInventory());
    }

    @DeleteMapping("/estoque/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/estoque/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

}
