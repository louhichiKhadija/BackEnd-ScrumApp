package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
		User findByEmail(String email);
}
