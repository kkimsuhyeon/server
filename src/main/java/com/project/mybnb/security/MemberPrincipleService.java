package com.project.mybnb.security;

import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import com.project.mybnb.businessMember.repository.BusinessMemberRepository;
import com.project.mybnb.consumer.dto.ConsumerDto;
import com.project.mybnb.consumer.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MemberPrincipleService implements UserDetailsService {

    private final BusinessMemberRepository businessMemberRepository;
    private final ConsumerRepository consumerRepository;

    @Autowired
    public MemberPrincipleService(BusinessMemberRepository businessMemberRepository, ConsumerRepository consumerRepository) {
        this.businessMemberRepository = businessMemberRepository;
        this.consumerRepository = consumerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        if (businessMemberRepository.findByEmail(username).isPresent()) {
            BusinessMemberDto businessMemberDto = businessMemberRepository.findByEmail(username).map(BusinessMemberDto::fromEntity).get();

            GrantedAuthority auth = new SimpleGrantedAuthority("BUSINESS");
            authorities.add(auth);

            return MemberPrinciple.fromBusinessMemberDto(businessMemberDto, authorities);
        }

        if (consumerRepository.findByEmail(username).isPresent()) {

            ConsumerDto consumerDto = consumerRepository.findByEmail(username).map(ConsumerDto::fromEntity).get();

            GrantedAuthority auth = new SimpleGrantedAuthority("CONSUMER");
            authorities.add(auth);

            return MemberPrinciple.fromConsumerDto(consumerDto, authorities);
        }

        return null;
    }
}
