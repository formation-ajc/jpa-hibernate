package org.example.repository;

import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Supplier;


public class SupplierRepository extends EntityRepository<Supplier, Integer> {
    public SupplierRepository(EntityManagerFactory emf) {
        super(emf, Supplier.class);
    }
}
