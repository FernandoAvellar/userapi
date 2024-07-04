package com.avellar.userapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avellar.userapi.entities.Coin;
import com.avellar.userapi.repositories.CoinRepository;

@Service
public class CoinService {

	@Autowired
	private CoinRepository repository;

	public List<Coin> findAll() {
		return repository.findAll();
	}

	public Coin findById(Long id) {
		Optional<Coin> obj = repository.findById(id);
		return obj.get();
	}

	public Coin findByCode(String code) {
		Coin coin = repository.findByCode(code);
		return coin;
	}

	public Coin insert(Coin obj) {
		return repository.save(obj);
	}


	public void delete(String code) {
		repository.deleteById(findByCode(code).getId());
	}

	public Coin update(Long id, Coin obj) {
		Coin entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Coin entity, Coin obj) {
		entity.setCode(obj.getCode());
		entity.setDescription(obj.getDescription());
	}

}
