package com.project.mybnb.product.dto.request;

import com.project.mybnb.product.dto.ProductDto;
import jakarta.validation.constraints.NotNull;

public record RequestCreateProductDto(
        @NotNull
        String title,

        String content,

        String category
) implements RequestProductDto {

    @Override
    public ProductDto toDto() {
        return new ProductDto.Builder(title).content(content).status().build();
    }
}
