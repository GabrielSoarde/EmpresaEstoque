package com.soardev.estoque_soardev.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Insira o nome do produto!")
    private String productName;

    private String description;

    @Positive(message = "Quantidade deve ser acima de 1")
    private Integer inventoryQuantity;

    @DecimalMin(value = "0.01", message = "Preço não pode ser zero ou negativo")
    @Digits(integer = 10, fraction = 2, message = "Preço inválido!")
    private BigDecimal price;

    @Column(name = "data_cadastro")
    private LocalDateTime registrationDate;

    @PrePersist
    public void prePersist(){
        this.registrationDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.registrationDate = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryMovement> inventoryMovement = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    public enum ProductCategory{
        ELETRONICOS,
        COMIDAS,
        ROUPAS,
        OUTROS
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(Integer inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<InventoryMovement> getInventoryMovement() {
        return inventoryMovement;
    }

    public void setInventoryMovement(List<InventoryMovement> inventoryMovement) {
        this.inventoryMovement = inventoryMovement;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
