package com.example.currencyApp.model;

import java.util.List;

public class CurrencyExchangeResponse {
    private String from;
    private List<CurrencyExchangeResult> currencies;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<CurrencyExchangeResult> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyExchangeResult> currencies) {
        this.currencies = currencies;
    }
}
