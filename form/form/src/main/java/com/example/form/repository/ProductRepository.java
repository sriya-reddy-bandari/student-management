package com.example.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.form.model.Products;

public interface ProductRepository extends JpaRepository<Products, String> {

}
