package org.example.repository;

import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Customer;
import org.example.repository.base.EntityRepository;


public class CustomerRepository extends EntityRepository<Customer, Integer> {

    public CustomerRepository(EntityManagerFactory emf) {
        super(emf, Customer.class);
    }

}
