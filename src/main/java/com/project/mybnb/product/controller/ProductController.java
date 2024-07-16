package com.project.mybnb.product.controller;

import com.project.mybnb.product.domain.Product;
import com.project.mybnb.product.service.ProductService;
import com.project.mybnb.security.MemberPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "")
    public ResponseEntity<?> getProducts(@AuthenticationPrincipal MemberPrinciple principle) {
        List<Product> products = productService.getProductsByBusinessMemberId(principle.id());

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
