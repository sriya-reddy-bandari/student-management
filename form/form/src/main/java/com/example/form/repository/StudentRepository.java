package com.example.form.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.form.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    
	List<Student> findByStatus(String status);

	
}
