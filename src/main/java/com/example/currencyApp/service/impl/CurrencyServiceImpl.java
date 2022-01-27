package com.example.currencyApp.service.impl;

import com.example.currencyApp.model.CurrencyExchangeInput;
import com.example.currencyApp.model.CurrencyExchangeResponse;
import com.example.currencyApp.model.CurrencyExchangeResult;
import com.example.currencyApp.model.CurrencyRates;
import com.example.currencyApp.service.api.CurrencyService;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.domain.Coins.CoinList;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public CurrencyRates getCurrencies(List<String> filter, String currency) {
        String lowerCaseCurrency = currency.toLowerCase();
        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
        List<CoinList> coinList = client.getCoinList();
        String lowerCaseFilter = prepareFilterParameter(filter, coinList);
        Optional<CoinList> foundCurrency = coinList.stream().filter(coin -> coin.getSymbol().equals(lowerCaseCurrency)).findFirst();
        Map<String, Map<String, Double>> response = findRates(foundCurrency, lowerCaseFilter, client, currency);
        CurrencyRates currencyRates = new CurrencyRates();
        currencyRates.setSource(currency);
        currencyRates.setRates(response.get(foundCurrency.get().getId()));
        client.shutdown();
        return currencyRates;
    }

    private Map<String, Map<String, Double>> findRates(Optional<CoinList> foundCurrency,
                                                       String lowerCaseFilter,
                                                       CoinGeckoApiClient client,
                                                       String currency) {
        Map<String, Map<String, Double>> response;
        if (foundCurrency.isPresent()) {
            response = client.getPrice(foundCurrency.get().getId(), lowerCaseFilter);
            System.out.println(response);
        } else {
            throw new InvalidParameterException("Currency " + currency + " not found");
        }
        return response;
    }

    private String prepareFilterParameter(List<String> filter, List<CoinList> coinList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (filter == null) {
            filter = new ArrayList<>();
            for (CoinList list : coinList) {
                filter.add(list.getSymbol());
            }
        }
        boolean isFirst = true;
        for (String s : filter) {
            if (isFirst) {
                stringBuilder.append(s.toLowerCase());
                isFirst = false;
            } else {
                stringBuilder.append(",").append(s.toLowerCase());
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public CurrencyExchangeResponse exchange(CurrencyExchangeInput currencyExchangeInput) {
        CurrencyRates currencies = getCurrencies(currencyExchangeInput.getTo(), currencyExchangeInput.getFrom());
        CurrencyExchangeResponse currencyExchangeResponse = new CurrencyExchangeResponse();
        currencyExchangeResponse.setFrom(currencyExchangeInput.getFrom());
        List<CurrencyExchangeResult> currencyExchangeResultList = new ArrayList<>();
        for (String targetCurrency : currencyExchangeInput.getTo()) {
            CurrencyExchangeResult currencyExchangeResult = new CurrencyExchangeResult();
            currencyExchangeResult.setName(targetCurrency);
            currencyExchangeResult.setAmount(currencyExchangeInput.getAmount());
            currencyExchangeResult.setRate(currencies.getRates().get(targetCurrency.toLowerCase()) * currencyExchangeInput.getAmount());
            currencyExchangeResult.setFee(currencyExchangeResult.getRate() * 0.01);
            currencyExchangeResult.setResult(currencyExchangeResult.getFee() + currencyExchangeResult.getRate());
            currencyExchangeResultList.add(currencyExchangeResult);
        }
        currencyExchangeResponse.setCurrencies(currencyExchangeResultList);
        return currencyExchangeResponse;
    }
}
