package com.project.mybnb.consumer.domain;

import com.project.mybnb.consumer.domain.MemberAuditingFields;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Consumer extends MemberAuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumer_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Setter
    private String name;

    @Setter
    private String refreshToken;
}
