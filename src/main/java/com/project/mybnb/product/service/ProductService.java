package com.project.mybnb.product.service;

import com.project.mybnb.product.dto.ProductDto;
import com.project.mybnb.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductDto dto) {

//        productRepository.save();
    }

    public void updateProduct() {

    }

    public void deleteProduct(Long id) {

    }

    public void findProductById(Long id) {

    }


}
