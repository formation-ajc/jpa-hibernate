package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EntityRepository<T, ID> {

    protected final EntityManagerFactory emf;
    private final Class<T> tClass;

    public EntityRepository(EntityManagerFactory emf, Class<T> tClass) {
        this.emf = emf;
        this.tClass = tClass;
    }

    public T getById(ID id){
        T entity = null;
        try(EntityManager em = this.emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            entity = em.find(this.tClass, id);

            transaction.commit();
        }
        catch (Exception e) {
            System.out.println("!!! {getById} error !!!");
        }
        return entity;
    }

    public List<T> getAll(){
        List<T> entities = null;
        try(EntityManager em = this.emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            TypedQuery<T> typedQuery =  em.createQuery("SELECT entity FROM "+ this.tClass.getName() +" entity", this.tClass);

            entities = typedQuery.getResultList();

            transaction.commit();
        }
        catch (Exception e) {
            System.out.println("!!! {getAll} error !!!");
        }
        return entities;
    }

    public T create(T entity) {
        try(EntityManager em = this.emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.persist(entity);

            transaction.commit();
            System.out.println("--- Ajout effectué ---");
        }
        catch (Exception e) {
            System.out.println("!!! {create} error !!!");
        }
        return entity;
    }

    public void update(T entity) {
        try(EntityManager em = this.emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.merge(entity);

            transaction.commit();
            System.out.println("--- Mise à jour effectuée ---");
        }
        catch (Exception e) {
            System.out.println("!!! {update} error !!!");
        }
    }

    public void delete(ID id) {
        try (EntityManager em = this.emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            em.remove(em.find(this.tClass, id));

            transaction.commit();
            System.out.println("--- Suppression effectuée ---");

        } catch (Exception e) {
            System.out.println("!!! {delete} error !!!");
        }
    }

}
