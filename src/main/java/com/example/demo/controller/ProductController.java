package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> fetchProductList()
    {
        return productService.fetchProductList();
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);

    }
}

//http://localhost:8080/api/product/products
//http://localhost:8080/api/product/create