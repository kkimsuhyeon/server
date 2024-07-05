package com.project.mybnb.article.dto;

import com.project.mybnb.article.domain.Article;
import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArticleResponseDto {

    private long article_id;
    private String content;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String location;

    public static ArticleResponseDto fromEntity(Article article) {
        return ArticleResponseDto.builder()
                .article_id(article.getArticle_id())
                .content(article.getContent())
                .category(article.getCategory())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .location(article.getLocation())
                .build();
    }
}
