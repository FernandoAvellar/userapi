package com.avellar.userapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.avellar.userapi.entities.Coin;
import com.avellar.userapi.services.CoinService;

@RestController
@RequestMapping(value = "/coins")
public class CoinController {

	@Autowired
	private CoinService service;

	@GetMapping
	public ResponseEntity<List<Coin>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{code}")
	public ResponseEntity<Coin> findByCode(@PathVariable String code) {
		Coin coin = service.findByCode(code);
		return ResponseEntity.ok().body(coin);
	}

	@PostMapping
	public ResponseEntity<Coin> insert(@RequestBody Coin obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	
	@DeleteMapping(value = "/{code}")
	public ResponseEntity<Void> delete(@PathVariable String code) {
		service.delete(code);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Coin> update(@PathVariable Long id, @RequestBody Coin obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);

	}

}
