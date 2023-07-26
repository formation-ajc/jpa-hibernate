package org.example.repository;

import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Supplier;
import org.example.repository.base.EntityRepository;


public class SupplierRepository extends EntityRepository<Supplier, Integer> {
    public SupplierRepository(EntityManagerFactory emf) {
        super(emf, Supplier.class);
    }
}
