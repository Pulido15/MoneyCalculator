package persistence;

import java.util.Date;
import model.Currency;
import model.ExchangeRate;

public class MockExchangeRateLoader implements ExchangeRateLoader{
    
    private static MockExchangeRateLoader instance;
     
    private MockExchangeRateLoader(){
    }
     
    public static MockExchangeRateLoader getInstance() {
        if (instance == null)
            instance = new MockExchangeRateLoader();
        return instance;
    }
    
    @Override
    public ExchangeRate load(Currency fromCurrency, Currency toCurrency){
        return load(new Date(), fromCurrency, toCurrency);    
    }
    
    @Override
    public ExchangeRate load(Date date, Currency fromCurrency, Currency toCurrency){
        return new ExchangeRate(date, fromCurrency, toCurrency, new model.Number(2.5));
    }
}
