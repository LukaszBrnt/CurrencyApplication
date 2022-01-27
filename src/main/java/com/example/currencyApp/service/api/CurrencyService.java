package com.example.currencyApp.service.api;

import com.example.currencyApp.model.CurrencyRates;
import com.example.currencyApp.model.CurrencyExchangeInput;
import com.example.currencyApp.model.CurrencyExchangeResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
public interface CurrencyService {
    CurrencyRates getCurrencies(List<String> filter, String currency) throws URISyntaxException, IOException;

    CurrencyExchangeResponse exchange(CurrencyExchangeInput currencyExchangeInput);

}
