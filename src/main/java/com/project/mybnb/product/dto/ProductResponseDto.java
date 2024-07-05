package com.project.mybnb.product.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class ProductResponseDto {

    private long product_id;
    private String content;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String location;

}
