package org.example.repository;

import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Customer;


public class CustomerRepository extends EntityRepository<Customer, Integer>{

    public CustomerRepository(EntityManagerFactory emf) {
        super(emf, Customer.class);
    }

}
