package org.example.repository;

import jakarta.persistence.*;
import org.example.entity.User;
import org.example.repository.common.CommonRepository;

public class UserRepository extends CommonRepository<User, Integer> {

    public UserRepository(EntityManagerFactory emf) {
        super(emf, User.class);
    }

    public User getByUsername(String username){
        return query(entityManager -> {
            TypedQuery<User> typedQuery =  entityManager.createQuery("SELECT user FROM User user WHERE user.username = :username", User.class);
            typedQuery.setParameter("username", username);
            return typedQuery.getSingleResult();
        });
    }

    public int getNumUsername(String username){
        return query(entityManager -> {
            Query query =  entityManager.createQuery(
                    "SELECT " +
                            "MAX(REPLACE(user.username, :username, '')) " +
                            "FROM User user " +
                            "WHERE user.username LIKE concat(:username, '%')"
            );
            query.setParameter("username", username);

            return Integer.parseInt((String) query.getResultList().get(0));
        });
    }

    public User getByUsernameAndPassword(String username, String password){
        return query(entityManager -> {
            TypedQuery<User> typedQuery =  entityManager.createQuery("SELECT user FROM User user WHERE user.username = :username AND user.password = :password", User.class);
            typedQuery.setParameter("username", username);
            typedQuery.setParameter("password", password);
            return typedQuery.getSingleResult();
        });
    }
}
