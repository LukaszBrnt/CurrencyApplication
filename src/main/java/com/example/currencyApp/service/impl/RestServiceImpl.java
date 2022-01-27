package com.example.currencyApp.service.impl;

import com.example.currencyApp.model.CurrencyRates;
import com.example.currencyApp.model.CurrencyExchangeInput;
import com.example.currencyApp.model.CurrencyExchangeResponse;
import com.example.currencyApp.service.api.CurrencyService;
import com.example.currencyApp.service.api.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


@Service
public class RestServiceImpl implements RestService {
    @Autowired
    private CurrencyService currencyService;


    @Override
    public CurrencyRates getCurrencies(List<String> filter, String currency) throws URISyntaxException, IOException {
       return currencyService.getCurrencies(filter,currency);
    }

    @Override
    public CurrencyExchangeResponse exchange(CurrencyExchangeInput currencyExchangeInput) {
        return currencyService.exchange(currencyExchangeInput);
    }
}
