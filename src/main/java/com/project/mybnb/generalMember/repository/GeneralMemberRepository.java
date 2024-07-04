package com.project.mybnb.generalMember.repository;

import com.project.mybnb.generalMember.domain.GeneralMember;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralMemberRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void save(GeneralMember member) {
        entityManager.persist(member);
    }
}
