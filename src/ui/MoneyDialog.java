package ui;

import model.Money;

public interface MoneyDialog {
    
    public void show();
    
    public Money getMoney();
    
    public void setMoney(Money money);
}
