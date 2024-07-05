package com.project.mybnb.businessMember.domain;

import com.project.mybnb.article.domain.Article;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BusinessMember extends MemberAuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Setter
    private String name;

    @Setter
    private String companyName;

    @Setter
    private String refreshToken;

    @OneToMany(mappedBy = "businessMember", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();
}
