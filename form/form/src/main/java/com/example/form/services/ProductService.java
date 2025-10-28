package com.example.form.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.form.model.Products;
import com.example.form.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Products saveProducts(Products products) {
        return productRepository.save(products);
    }

    public List<Products> getProducts() {
        return productRepository.findAll();
    }

}
