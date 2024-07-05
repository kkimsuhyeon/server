package com.project.mybnb.product.mapper;


import com.project.mybnb.product.domain.Product;
import com.project.mybnb.product.dto.ProductPatchDto;
import com.project.mybnb.product.dto.ProductPostDto;
import com.project.mybnb.product.dto.ProductResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductPostDto createProductDto(ProductPostDto articleDto);

    ProductResponseDto createToResposnseDto(Product product);

    ProductResponseDto updateToResposnseDto(Product product);

    ProductPatchDto updateProductDto(ProductPatchDto productPatchDto);

    List<Product> productToResponesDto(List<Product> productList);
}
