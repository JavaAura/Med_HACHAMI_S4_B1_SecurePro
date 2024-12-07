package com.yc.SecurePro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "designation")
    @NotBlank(message = "Designation is required")
    private String designation;

    @Column(name = "prix")
    @NotNull(message = "Prix is required")
    @Min(value = 0, message = "Prix must be greater than 0")
    private double prix;

    @Column(name = "quantite")
    @NotNull(message = "Quantite is required")
    @Min(value = 0, message = "Quantite must be greater than 0")
    private int quantity;

   
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    public Product() {
    }

    public Product(String designation, double prix, int quantity, Category category) {
        this.designation = designation;
        this.prix = prix;
        this.quantity = quantity;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", designation=" + designation + ", prix=" + prix + ", quantite=" + quantity
                + ", category=" + category.getName() + "]";
    }
}
