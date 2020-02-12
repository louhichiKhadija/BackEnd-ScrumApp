package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
