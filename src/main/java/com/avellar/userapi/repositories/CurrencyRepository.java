package com.avellar.userapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avellar.userapi.entities.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	Optional<Currency> findByCurrency(String currency);
}
