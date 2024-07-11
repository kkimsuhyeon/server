package com.project.mybnb.consumer.repository;

import com.project.mybnb.consumer.domain.Consumer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsumerRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Optional<Consumer> findById(Long id) {
        Consumer result = entityManager.find(Consumer.class, id);
        return Optional.ofNullable(result);
    }

    public Optional<Consumer> findByEmail(String email) {
        String query = "SELECT c FROM Consumer AS c" +
                " WHERE c.email = :email";

        List<Consumer> members = entityManager.createQuery(query, Consumer.class)
                .setParameter("email", email)
                .getResultList();

        return members.stream().findFirst();
    }

    public Consumer save(Consumer comsumer) {
        entityManager.persist(comsumer);
        return comsumer;
    }

    public void flush() {
        entityManager.flush();
        entityManager.clear();
    }

}
