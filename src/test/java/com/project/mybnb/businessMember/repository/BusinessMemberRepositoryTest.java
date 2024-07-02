package com.project.mybnb.businessMember.repository;

import com.project.mybnb.businessMember.domain.BusinessMember;
import jakarta.persistence.NoResultException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({JpaConfig.class})
@Sql(scripts = {"classpath:testData.sql"})
class BusinessMemberRepositoryTest {

    private final BusinessMemberRepository repository;

    @Autowired
    public BusinessMemberRepositoryTest(BusinessMemberRepository repository) {
        this.repository = repository;
    }

    @Test
    @DisplayName("id를 통해서 유저 찾아오기")
    public void findByIdTest() throws Exception {

        Long id = 1L;
        BusinessMember member = repository.findById(id).orElseThrow(() -> {
            Assertions.fail("찾지 못함");
            return new Exception("???");
        });

        Assertions.assertThat(member)
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("email", "test1@test.com");
    }

    @Test
    @DisplayName("id를 통해서 user를 찾지 못함")
    public void noFindByIdTest() {
        Assertions.assertThat(repository.findById(10000L)).isEmpty();
    }

    @Test
    @DisplayName("email를 통해서 유저 찾아오기")
    public void findByEmailTest() throws Exception {

        String email = "test1@test.com";

        BusinessMember member = repository.findByEmail(email).orElseThrow(() -> {
            Assertions.fail("찾지 못함");
            return new Exception("???");
        });

        Assertions.assertThat(member)
                .isNotNull()
                .hasFieldOrPropertyWithValue("email", email);
    }

    @Test
    @DisplayName("email을 통해서 user를 찾지 못함")
    public void noFindByEmailTest() {
        assertThrows(NoResultException.class, () -> repository.findByEmail("asdkfjaskldf"));
    }

    @Test
    @DisplayName("user 생성")
    public void saveTest() throws Exception {
        String email = "email";
        String password = "password";
        BusinessMember member = createMember(email, password);

        repository.save(member);
        repository.flush();

        BusinessMember result = repository.findByEmail(email).orElseThrow(() -> {
            Assertions.fail("찾지 못함");
            return new Exception("???");
        });

        Assertions.assertThat(result)
                .hasFieldOrPropertyWithValue("email", email)
                .hasFieldOrPropertyWithValue("password", password);
    }

    private BusinessMember createMember(String email, String password) {
        return BusinessMember.builder()
                .email(email)
                .password(password)
                .name("name")
                .companyName("company")
                .build();
    }
}

@Configuration
class JpaConfig {

    @Bean
    public BusinessMemberRepository businessMemberRepository() {
        return new BusinessMemberRepository();
    }
}