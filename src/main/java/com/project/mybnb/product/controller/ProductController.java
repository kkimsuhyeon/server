package com.project.mybnb.product.controller;

import com.project.mybnb.product.domain.Product;
import com.project.mybnb.product.dto.ProductPatchDto;
import com.project.mybnb.product.dto.ProductPostDto;
import com.project.mybnb.product.mapper.ProductMapper;
import com.project.mybnb.product.repository.ProductRepository;
import com.project.mybnb.product.service.ProductService;
import com.project.mybnb.dto.MultiResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    //숙소 등록
    @PostMapping("/posts")
    public ResponseEntity createProduct(@Valid @RequestBody ProductPostDto productPostDto) {

      Product product = productService.createProduct(productMapper.createProductDto(productPostDto));

      return  new ResponseEntity (productMapper.createToResposnseDto(product),HttpStatus.CREATED);

    }

    //숙소 수정
    @PatchMapping("/{product-id}")
    public ResponseEntity updateProduct(@Valid @RequestBody ProductPatchDto productPatchDto,
    @PathVariable("product-id") long product_id) throws Exception {

        Product product = productService.updateProduct(productMapper.updateProductDto(productPatchDto),product_id);

        return new ResponseEntity (productMapper.updateToResposnseDto(product),HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity deleteArticle( @PathVariable("product-id") long product_id) throws Exception{

        productService.deleteProduct(product_id);

        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //숙소 리스트 조회
    @GetMapping("/{product-id}/list")
    public ResponseEntity getArticleList( @Valid @PathVariable("product-id")long product_id,
                                        @Positive @RequestParam("page")int page,
                                        @Positive @RequestParam("size") int size){

        Page<Product> productPage= productService.getProductList(page-1, size);

        List<Product> productList = productPage.getContent();

        return  new ResponseEntity<>(
                new MultiResponseDto<>(productMapper.productToResponesDto(productList),
                        productPage),
                HttpStatus.OK
        );


    }


    //숙소 상세조회
    @GetMapping("/{product-id}")
    public ResponseEntity getProducts( @PathVariable("product-id") long product_id){

        Product product = productService.getProducts(product_id);

        return new ResponseEntity(productMapper.createToResposnseDto(product),HttpStatus.OK);
    }



}
