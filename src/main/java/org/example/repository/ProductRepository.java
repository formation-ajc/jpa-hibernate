package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.entity.Product;
import org.example.entity.Supplier;
import org.example.repository.base.EntityRepository;

import java.util.List;

public class ProductRepository extends EntityRepository<Product, Integer> {
    public ProductRepository(EntityManagerFactory emf) {
        super(emf, Product.class);
    }

    public Product getByNamePriceAndSupplier(String name, Float price, Supplier supplier) {
        Product product = null;
        try(EntityManager em = this.emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            TypedQuery<Product> typedQuery =  em.createQuery("SELECT product FROM Product product WHERE product.name = :name AND product.price = :price AND product.supplier = :supplier", Product.class);
            typedQuery.setParameter("name", name);
            typedQuery.setParameter("price", price);
            typedQuery.setParameter("supplier", supplier);

            product = typedQuery.getSingleResult();

            transaction.commit();
        }
        catch (Exception e) {
            System.out.println("!!! Produit inexistant !!!");
        }
        return product;
    }

    public List<Product> getAllBySupplier(Supplier supplier) {
        List<Product> products = null;
        try(EntityManager em = this.emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            TypedQuery<Product> typedQuery =  em.createQuery("SELECT product FROM Product product WHERE product.supplier = :supplier", Product.class);
            typedQuery.setParameter("supplier", supplier);

            products = typedQuery.getResultList();

            transaction.commit();
        }
        catch (Exception e) {
            System.out.println("!!! {getAllBySupplier} error !!!");
        }
        return products;
    }
}
