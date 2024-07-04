package com.avellar.userapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avellar.userapi.entities.Coin;

public interface CoinRepository extends JpaRepository<Coin, Long> {
	@Query(value = "SELECT * FROM tb_coin WHERE code=?", nativeQuery = true)
	public Coin findByCode(String code);

}
