package com.project.mybnb.generalMember.repository;

import com.project.mybnb.generalMember.GeneralMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralMemberRepository extends JpaRepository<GeneralMember, Long> {
    GeneralMember findByEmail(String email);
}
