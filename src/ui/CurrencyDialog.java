package ui;

import model.Currency;

public interface CurrencyDialog {
    
    public void show();
    
    public Currency getCurrency();
    
    public void setCurrency(Currency currency);
}
