package com.avellar.userapi.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.avellar.userapi.entities.Currency;
import com.avellar.userapi.repositories.CurrencyRepository;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private SettingsService settingsService;

	private static final String API_URL = "https://economia.awesomeapi.com.br/json/last/";
	private static final Logger LOGGER = Logger.getLogger(CurrencyService.class.getName());

	public List<Currency> findAll() {
		return currencyRepository.findAll();
	}

	public void updateCurrency() {
		Set<String> currencies = settingsService.getAllowedCurrencies();
		String symbols = String.join("-BRL,", currencies) + "-BRL";
		String url = API_URL + symbols;
		LOGGER.info("Request URL: " + url);

		try {
			RestTemplate restTemplate = new RestTemplate();
			@SuppressWarnings("unchecked")
			Map<String, Map<String, String>> response = restTemplate.getForObject(url, Map.class);
			LOGGER.info("Response from API: " + response);

			if (response != null) {
				for (String key : response.keySet()) {
					Map<String, String> currencyData = response.get(key);
					saveOrUpdateCurrencyData(currencyData);
				}
			}

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error updating currency rates", e);
		}
	}

	private void saveOrUpdateCurrencyData(Map<String, String> data) {
		String currencyCode = data.get("code");
		Currency currency = currencyRepository.findByCurrency(currencyCode).orElse(new Currency());

		currency.setCurrency(currencyCode);
		currency.setBid(Double.parseDouble(data.get("bid")));
		long timestamp = Long.parseLong(data.get("timestamp"));
		LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
		currency.setTimestamp(dateTime);

		currencyRepository.save(currency);
	}

}
