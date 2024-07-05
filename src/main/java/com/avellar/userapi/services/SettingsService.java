package com.avellar.userapi.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

	@Value("${allowed.currencies}")
	private String allowedCurrencies;

	private final String propertiesFilePath = "src/main/resources/application.properties";

	public Set<String> getAllowedCurrencies() {
		return new HashSet<>(Arrays.asList(allowedCurrencies.split(",")));
	}

	public boolean addCurrency(String currency) {
		Set<String> currencies = getAllowedCurrencies();
		if (currencies.add(currency.toUpperCase())) {
			return updatePropertiesFile(currencies);
		}
		return false;
	}

	public boolean removeCurrency(String currency) {
		Set<String> currencies = getAllowedCurrencies();
		if (currencies.remove(currency)) {
			return updatePropertiesFile(currencies);
		}
		return false;
	}
	
	private boolean updatePropertiesFile(Set<String> currencies) {
        try (FileInputStream input = new FileInputStream(propertiesFilePath)) {
            Properties properties = new Properties();
            properties.load(input);

            properties.setProperty("allowed.currencies", String.join(",", currencies));

            try (FileOutputStream output = new FileOutputStream(propertiesFilePath)) {
                properties.store(output, null);
            }

            // Recarregar propriedades após atualização
            allowedCurrencies = String.join(",", currencies);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
