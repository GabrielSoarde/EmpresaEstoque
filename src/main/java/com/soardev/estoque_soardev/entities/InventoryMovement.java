package com.soardev.estoque_soardev.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao_estoque")
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Product product;

    @Positive(message = "Quantidade de movimentação deve ser positiva")
    private Integer quantity;

    private String observation;

    @Column(name = "data_movimentacao")
    private LocalDateTime movDate;

    @PrePersist
    public void prePersist(){
        this.movDate = LocalDateTime.now();
    }

    @Enumerated(EnumType.STRING)
    private MovType movType;

    public enum MovType{
        ENTRADA,
        SAIDA,
        AJUSTE
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public LocalDateTime getMovDate() {
        return movDate;
    }

    public void setMovDate(LocalDateTime movDate) {
        this.movDate = movDate;
    }

    public MovType getMovType() {
        return movType;
    }

    public void setMovType(MovType movType) {
        this.movType = movType;
    }
}
