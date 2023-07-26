package org.example.repository;

import jakarta.persistence.*;
import org.example.entity.User;

public class UserRepository extends EntityRepository<User, Integer> {

    public UserRepository(EntityManagerFactory emf) {
        super(emf, User.class);
    }

    public User getByUsername(String username){
        User user = null;
        try(EntityManager em = this.emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            TypedQuery<User> typedQuery =  em.createQuery("SELECT user FROM User user WHERE user.username = :username", User.class);
            typedQuery.setParameter("username", username);

            user = typedQuery.getSingleResult();

            transaction.commit();
        }
        catch (Exception e) {
            System.out.println("!!! Nom d'utilisateur inexistant !!!");
        }
        return user;
    }

    public int getNumUsername(String username){
        int nb = 0;
        try(EntityManager em = this.emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            Query query =  em.createQuery(
                    "SELECT " +
                            "MAX(REPLACE(user.username, :username, '')) " +
                    "FROM User user " +
                    "WHERE user.username LIKE concat(:username, '%')"
            );
            query.setParameter("username", username);

            nb = Integer.parseInt((String) query.getResultList().get(0));

            transaction.commit();
        }
        catch (Exception e) {
            System.out.println("!!! ERREUR !!!");
        }
        return nb;
    }

    public User getByUsernameAndPassword(String username, String password){
        User user = null;
        try(EntityManager em = this.emf.createEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            TypedQuery<User> typedQuery =  em.createQuery("SELECT user FROM User user WHERE user.username = :username AND user.password = :password", User.class);
            typedQuery.setParameter("username", username);
            typedQuery.setParameter("password", password);

            user = typedQuery.getSingleResult();

            transaction.commit();
        }
        catch (Exception e) {
            System.out.println("!!! Nom d'utilisateur ou mot de passe introuvable !!!");
        }
        return user;
    }
}
