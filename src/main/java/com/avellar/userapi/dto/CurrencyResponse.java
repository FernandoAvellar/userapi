package com.avellar.userapi.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public record CurrencyResponse(
    @JsonProperty("rates") Map<String, CurrencyData> rates) {

    public CurrencyResponse() {
        this(new HashMap<>());
    }

    @JsonAnySetter
    public void addRate(String key, CurrencyData value) {
        rates.put(key, value);
    }

    public record CurrencyData(
            @JsonProperty("code") String code,
            @JsonProperty("codein") String codein,
            @JsonProperty("name") String name,
            @JsonProperty("high") String high,
            @JsonProperty("low") String low,
            @JsonProperty("varBid") String varBid,
            @JsonProperty("pctChange") String pctChange,
            @JsonProperty("bid") String bid,
            @JsonProperty("ask") String ask,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("create_date") String createDate) {
    }
}
