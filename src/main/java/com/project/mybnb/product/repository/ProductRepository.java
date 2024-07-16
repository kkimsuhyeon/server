package com.project.mybnb.product.repository;

import com.project.mybnb.product.domain.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Product product) {
        entityManager.persist(product);
    }

    public List<Product> getProductsByBusinessMemberId(Long memberId) {
        String query = "SELECT p FROM Product AS p" +
                " WHERE p.businessMember.id = :memberId";

        List<Product> resultList = entityManager.createQuery(query, Product.class).setParameter("memberId", memberId).getResultList();

        return resultList;
    }


}
