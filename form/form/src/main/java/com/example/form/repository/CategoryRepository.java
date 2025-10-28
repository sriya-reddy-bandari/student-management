package com.example.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.form.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
