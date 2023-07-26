package org.example.repository;

import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Supplier;
import org.example.repository.common.CommonRepository;


public class SupplierRepository extends CommonRepository<Supplier, Integer> {
    public SupplierRepository(EntityManagerFactory emf) {
        super(emf, Supplier.class);
    }
}
