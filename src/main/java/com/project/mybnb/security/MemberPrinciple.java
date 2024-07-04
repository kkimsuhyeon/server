package com.project.mybnb.security;

import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record MemberPrinciple(
        Long id,
        String email,
        String password,
        String name,
        Collection<? extends GrantedAuthority> authorities
) implements UserDetails {

    public static MemberPrinciple of(Long id, String email, String password, String name, Collection<? extends GrantedAuthority> authorities) {
        return new MemberPrinciple(id, email, password, name, authorities);
    }

    public static MemberPrinciple fromBusinessMemberDto(BusinessMemberDto dto, Collection<? extends GrantedAuthority> authorities) {
        return MemberPrinciple.of(dto.getId(), dto.getEmail(), dto.getPassword(), dto.getName(), authorities);
    }

    public BusinessMemberDto toDto() {
        return new BusinessMemberDto.Builder(email, password).name(name).id(id).build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
