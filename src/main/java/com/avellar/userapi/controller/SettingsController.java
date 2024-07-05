package com.avellar.userapi.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avellar.userapi.dto.CurrencyRequest;
import com.avellar.userapi.services.SettingsService;

@RestController
@RequestMapping("/settings")
public class SettingsController {

	@Autowired
	private SettingsService settingsService;

	@GetMapping
	public ResponseEntity<Set<String>> getAllowedCurrencies() {
		return ResponseEntity.ok(settingsService.getAllowedCurrencies());
	}
	
	@PostMapping
    public ResponseEntity<Void> addCurrency(@RequestBody CurrencyRequest request) {
        if (settingsService.addCurrency(request.currency())) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
	
	
	//ToDo: Quando removo uma moeda da lista preciso garantir que se houver já alguma persistência da sua cotação na base que essa também seja removida.
    @DeleteMapping("/{currency}")
    public ResponseEntity<Void> removeCurrency(@PathVariable String currency) {
        if (settingsService.removeCurrency(currency)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
