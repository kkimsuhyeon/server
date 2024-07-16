package com.project.mybnb.product.dto;

import com.project.mybnb.product.domain.ProductStatus;

public class ProductDto {

    private Long id;
    private String title;
    private String content;
    private String category;
    private ProductStatus status;

    public ProductDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.content = builder.content;
        this.status = builder.status;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String content;
        private String category;
        private ProductStatus status;

        public Builder(String title) {
            this.title = title;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder status(ProductStatus status) {
            this.status = status;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(this);
        }
    }

}
