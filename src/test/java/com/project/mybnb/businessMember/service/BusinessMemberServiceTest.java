package com.project.mybnb.businessMember.service;

import com.project.mybnb.businessMember.domain.BusinessMember;
import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import com.project.mybnb.businessMember.repository.BusinessMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessMemberServiceTest {

    @InjectMocks
    private BusinessMemberService service;

    @Mock
    private BusinessMemberRepository repository;

    @Test
    @DisplayName("user 생성")
    public void saveTest() {
        String email = "email";
        String password = "password";
        BusinessMemberDto memberDto = createMemberDto(email, password, "name");
        given(repository.save(any(BusinessMember.class))).willReturn(createMember(email, password, "name"));
        service.saveBusinessMember(memberDto);
        then(repository).should().save(any(BusinessMember.class));
    }

    @Test
    @DisplayName("user 수정")
    public void updateTest() {
        // Given
        Long memberId = 1L;
        String email = "email";
        String password = "password";
        String name = "name";

        String newEmail = "new email";
        String newPassword = "new password";
        String newName = "new name";
        BusinessMember member = createMember(email, password, name);
        BusinessMemberDto memberDto = createMemberDto(newEmail, newPassword, newName);

        given(repository.findById(member.getId())).willReturn(Optional.of(member));
        service.updateBusinessMember(member.getId(), memberDto);

        Assertions.assertThat(member)
                .hasFieldOrPropertyWithValue("email", email) // email은 변경되지 않음
                .hasFieldOrPropertyWithValue("password", newPassword)
                .hasFieldOrPropertyWithValue("name", newName);
    }

    @Test
    @DisplayName("user 삭제")
    public void deleteTest() {
        BusinessMember member = createMember("email", "password", "name");
        given(repository.findById(member.getId())).willReturn(Optional.of(member));
        service.deleteBusinessMember(member.getId());
        Assertions.assertThat(member.getExpiredAt()).isNotNull();
    }

    private BusinessMemberDto createMemberDto(String email, String password, String name) {
        return new BusinessMemberDto.Builder(email, password).name(name).build();
    }

    private BusinessMember createMember(String email, String password, String name) {
        return BusinessMember.builder().email(email).password(password).name(name).build();
    }

}