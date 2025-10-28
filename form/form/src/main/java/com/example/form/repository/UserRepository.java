package com.example.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.form.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

}
