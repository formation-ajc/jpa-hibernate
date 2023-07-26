package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    private Float price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name="idSupplier")
    private Supplier supplier;

    public Product() {}

    public Product(String name, Float price, Integer quantity, Supplier supplier) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public Product(Integer id, String name, Float price, Integer quantity, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String toDisplay() {
        return "Product{" +
            "id=" + this.id +
            ", name='" + this.name + '\'' +
            ", price=" + this.price +
            ", quantity=" + this.quantity +
            ", supplier=" + this.supplier.getName() +
            '}';
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + this.id +
            ", name='" + this.name + '\'' +
            ", price=" + this.price +
            ", quantity=" + this.quantity +
            ", supplier=" + this.supplier +
            '}';
    }
}
