package com.project.mybnb.product.dto;

import lombok.Data;

@Data
public class ProductPatchDto {
    private String title;
    private String content;
    private String category;
    private String location;
    private int price;

}
