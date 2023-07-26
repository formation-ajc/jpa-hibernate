package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Integer id;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @OneToOne
    @JoinColumn(name="idUser")
    private User user;

    public Customer() {}

    public Customer(String firstname, String lastname, User user) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.user = user;
    }

    public Customer(Integer id, String firstname, String lastname, User user) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + this.id +
                ", firstname='" + this.firstname + '\'' +
                ", lastname='" + this.lastname + '\'' +
                ", user=" + this.user +
                '}';
    }
}
