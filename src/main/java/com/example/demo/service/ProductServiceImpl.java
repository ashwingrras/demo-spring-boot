package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> fetchProductList() {
        return (List<Product>)
                productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {

        Product createdProduct = null;
        try {
            createdProduct = productRepository.save(product);
        } catch (Exception e) {
            System.out.println(" Exception is " + e);
        }

        return createdProduct;
    }

}
