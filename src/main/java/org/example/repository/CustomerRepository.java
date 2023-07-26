package org.example.repository;

import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Customer;
import org.example.repository.common.CommonRepository;


public class CustomerRepository extends CommonRepository<Customer, Integer> {

    public CustomerRepository(EntityManagerFactory emf) {
        super(emf, Customer.class);
    }

}
