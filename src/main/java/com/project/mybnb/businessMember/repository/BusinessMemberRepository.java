package com.project.mybnb.businessMember.repository;

import com.project.mybnb.businessMember.domain.BusinessMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BusinessMemberRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Optional<BusinessMember> findById(Long id) {
        BusinessMember result = entityManager.find(BusinessMember.class, id);
        return Optional.ofNullable(result);
    }

    public Optional<BusinessMember> findByEmail(String email) {
        String query = "SELECT bm FROM Consumer AS bm" +
                " WHERE bm.email = :email";

        List<BusinessMember> members = entityManager.createQuery(query, BusinessMember.class)
                .setParameter("email", email)
                .getResultList();

        return members.stream().findFirst();
    }

    public BusinessMember save(BusinessMember member) {
        entityManager.persist(member);
        return member;
    }

    public void flush() {
        entityManager.flush();
        entityManager.clear();
    }

}
