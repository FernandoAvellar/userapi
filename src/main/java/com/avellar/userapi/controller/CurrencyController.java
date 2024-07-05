package com.avellar.userapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avellar.userapi.entities.Currency;
import com.avellar.userapi.services.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;

	@GetMapping
	public ResponseEntity<List<Currency>> findAll() {
		return ResponseEntity.ok().body(currencyService.findAll());
	}

	@GetMapping("/update")
	public ResponseEntity<Void> updateCurrencyRates() {
		currencyService.updateCurrency();
		return ResponseEntity.ok().build();
	}

}
