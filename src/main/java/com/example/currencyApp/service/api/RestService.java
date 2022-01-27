package com.example.currencyApp.service.api;

import com.example.currencyApp.model.CurrencyRates;
import com.example.currencyApp.model.CurrencyExchangeInput;
import com.example.currencyApp.model.CurrencyExchangeResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Currency Application API
 */
@RestController
public interface RestService {
    /**
     * Gets rates for given currency.
     * @param filter currencies to show (gets all if not given)
     * @param currency given currency
     * @return source currency with given rates
     * @throws URISyntaxException
     * @throws IOException
     */
    @GetMapping("/currencies/{currency}")
    CurrencyRates getCurrencies(@RequestParam(required = false) List<String> filter, @PathVariable(name = "currency") String currency) throws URISyntaxException, IOException;

    /**
     * Shows predition of given currency
     * @param currencyExchangeInput request body
     * @return response with calculations of prediction
     */
    @PostMapping("/currencies/exchange")
    CurrencyExchangeResponse exchange(@RequestBody CurrencyExchangeInput currencyExchangeInput);

}
