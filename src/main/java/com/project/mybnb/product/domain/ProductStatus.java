package com.project.mybnb.product.domain;


import lombok.Getter;

@Getter
public enum ProductStatus {

    PRIVATE("비공개"),
    PUBLIC("공개"),
    DELETE("삭제");

    private final String description;

    ProductStatus(String description) {
        this.description = description;
    }
}
