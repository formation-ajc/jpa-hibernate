package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Integer id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @OneToOne
    @JoinColumn(name="idUser")
    private User user;
    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    public Supplier() {}

    public Supplier(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Supplier(Integer id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", user=" + this.user +
                '}';
    }
}
