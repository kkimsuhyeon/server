package com.project.mybnb.product.domain;

import com.project.mybnb.businessMember.domain.BusinessMember;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;

    private String title;

    private String content;

    private String category;

    private String status;

    @CreatedDate
    @Column(nullable = true, name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = true, name = "updated_at")
    private LocalDateTime updatedAt;

    private String location;

    private int price;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "business_member_id")
    private BusinessMember businessMember;
}
