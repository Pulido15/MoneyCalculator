package persistence;

import java.util.Date;
import model.Currency;
import model.ExchangeRate;

public interface ExchangeRateLoader {
    
    public ExchangeRate load(Currency fromCurrency, Currency toCurrency);
    public ExchangeRate load(Date date, Currency fromCurrency, Currency toCurrency);
}
