package com.project.mybnb.product.repository;

import com.project.mybnb.product.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Product product) {
        entityManager.persist(product);
    }


}
