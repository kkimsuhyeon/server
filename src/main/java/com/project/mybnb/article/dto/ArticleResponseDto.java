package com.project.mybnb.article.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class ArticleResponseDto {

    private long article_id;
    private String content;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String location;

}
