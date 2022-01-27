package com.example.currencyApp.model;

import java.util.Map;

public class CurrencyRates {
    private String source;
    private Map<String, Double> rates;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
