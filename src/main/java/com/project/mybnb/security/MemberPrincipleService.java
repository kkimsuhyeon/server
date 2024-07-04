package com.project.mybnb.security;

import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import com.project.mybnb.businessMember.repository.BusinessMemberRepository;
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

    @Autowired
    public MemberPrincipleService(BusinessMemberRepository businessMemberRepository) {
        this.businessMemberRepository = businessMemberRepository;
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

        return null;
    }
}
