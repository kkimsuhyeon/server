package com.project.mybnb.product.service;

import com.project.mybnb.product.domain.Product;
import com.project.mybnb.product.dto.ProductPatchDto;
import com.project.mybnb.product.dto.ProductPostDto;
import com.project.mybnb.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductPostDto productPostDto){

        Product product = new Product();

        product.setTitle(productPostDto.getTitle());
        product.setContent(productPostDto.getContent());
        product.setCategory(productPostDto.getCategory());
        product.setLocation(productPostDto.getLocation());
        product.setPrice(productPostDto.getPrice());

        return productRepository.save(product);

    }

    public Product updateProduct(ProductPatchDto productPatchDto, long product_id) throws Exception {

        Product product = findVerifiedProduct(product_id);

        product.setTitle(productPatchDto.getTitle());
        product.setContent(productPatchDto.getContent());
        product.setCategory(productPatchDto.getCategory());
        product.setLocation(productPatchDto.getLocation());
        product.setPrice(productPatchDto.getPrice());

        return productRepository.save(product);



    }

    //유효한 아이디인지 검증
    public Product findVerifiedProduct(long product_id) throws Exception {

        Optional<Product> findArticle= productRepository.findById(product_id);

        return findArticle.orElseThrow(()-> new Exception("유효하지않은 숙소 아이디 입니다"));

    }


    public void deleteProduct(long product_id) throws Exception {

        Product findProduct = findVerifiedProduct(product_id); //유효한 댓글인지 검증
        productRepository.delete(findProduct);
    }



    public Page<Product> getProductList(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size,
                Sort.by("createdAt").descending()));
    }


    public Product getProducts(long product_id) {

        Product product = productRepository.findById(product_id).orElse(null);

        return product;
    }

}
