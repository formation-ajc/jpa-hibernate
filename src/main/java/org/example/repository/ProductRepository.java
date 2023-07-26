package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entity.Product;
import org.example.entity.Supplier;
import org.example.repository.common.CommonRepository;

import java.util.List;

public class ProductRepository extends CommonRepository<Product, Integer> {
    public ProductRepository(EntityManagerFactory emf) {
        super(emf, Product.class);
    }

    public Product getByNamePriceAndSupplier(String name, Float price, Supplier supplier) {
        return query(entityManager -> {
            TypedQuery<Product> typedQuery =  entityManager.createQuery("SELECT product FROM Product product WHERE product.name = :name AND product.price = :price AND product.supplier = :supplier", Product.class);
            typedQuery.setParameter("name", name);
            typedQuery.setParameter("price", price);
            typedQuery.setParameter("supplier", supplier);
            return typedQuery.getSingleResult();
        });
    }

    public List<Product> getAllBySupplier(Supplier supplier) {
        return query(entityManager -> {
            TypedQuery<Product> typedQuery =  entityManager.createQuery("SELECT product FROM Product product WHERE product.supplier = :supplier", Product.class);
            typedQuery.setParameter("supplier", supplier);
            return typedQuery.getResultList();
        });
    }
}
