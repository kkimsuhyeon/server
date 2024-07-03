package com.project.mybnb.article.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class ArticleResponseDto {

    private String content;
    private String category;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
    private String location;

}
