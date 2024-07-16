package com.project.mybnb.security;

import com.project.mybnb.businessMember.repository.BusinessMemberRepository;
import com.project.mybnb.consumer.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired
    private BusinessMemberRepository businessMemberRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Bean
    public TokenProvider tokenProvider() {
        return new TokenProvider(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MemberPrincipleService(businessMemberRepository, consumerRepository);
    }

    @Bean
    public TokenVerificationFilter tokenVerificationFilter() {
        return new TokenVerificationFilter(tokenProvider());
    }
}
