package model;

import java.util.Date;

public class ExchangeRate{
    
    private Date date;
    private Number rate;
    private Currency fromCurrency, toCurrency;

    public ExchangeRate(Date date, Currency fromCurrency, Currency toCurrency, Number rate) {
        this.date = date;
        this.rate = rate;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }
    
    public Date getDate(){
        return date;
    }
    

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }
    
    public Number getRate() {
        return rate;
    }
}
