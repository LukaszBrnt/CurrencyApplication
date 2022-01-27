# Currency Application API
## Usage
The application has 2 endpoints:  
**GET**`/currencies/{currency}?{filter(optional)}`  
###parameters:  
* currency given currency
* filter currencies to show (gets all if not given)  
###example response:
```json
{
  "source":"BTC",
  "rates":{
  "USDT":0.3321,
  "ETH":0.2911
  }
  }
```  
###example of use:
`/currencies/BTC?filter=USD&filter=ETH`

**POST**`/currencies/exchange`
###parameters:
* currencyExchangeInput request body  
###example response:
```JSON
{
"from": "BTC",
"currencies": [
{
"name": "ETH",
"rate": 1838.240833,
"amount": 121.0,
"result": 1856.6232413300002,
"fee": 18.38240833
},
{
"name": "USD",
"rate": 4396656.0,
"amount": 121.0,
"result": 4440622.56,
"fee": 43966.56
}
]
}
```
###example of use:
`/currencies/exchange`

To test the solution for example Postman Application can be used.  

##Installation

To run the application you need to have one additional external library:  
https://github.com/Philipinho/CoinGecko-Java  
It needs to be loaded as external library to the project.
