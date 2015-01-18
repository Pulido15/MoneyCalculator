package control;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import model.Currency;
import model.ExchangeRate;
import model.Money;
import model.MoneyExchanger;
import persistence.ExchangeRateLoader;
import ui.console.ConsoleCurrencyDialog;
import ui.console.ConsoleDateDialog;
import ui.console.ConsoleMoneyDialog;
import ui.console.ConsoleMoneyViewer;

public class ExchangeMoneyControl{
    
    private ConsoleMoneyDialog moneyDialog;
    private ConsoleCurrencyDialog currencyDialog;
    private ConsoleMoneyViewer moneyViewer;
    private ExchangeRateLoader exchangeRateLoader;
    private ConsoleDateDialog dateDialog;

    public ExchangeMoneyControl(ConsoleMoneyDialog moneyDialog, ConsoleCurrencyDialog currencyDialog, ConsoleMoneyViewer moneyViewer, ExchangeRateLoader exchangeRateLoader, ConsoleDateDialog dateDialog) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.moneyViewer = moneyViewer;
        this.exchangeRateLoader = exchangeRateLoader;
        this.dateDialog = dateDialog;
    }
    
    public void execute() throws IOException, ParseException{
        Date date = readDate();
        Money money = readMoney();
        Currency currency = readCurrency();
        ExchangeRate exchangeRate = exchangeRateLoader.load(date, money.getCurrency(), currency);
        money = MoneyExchanger.exchange(money, exchangeRate);
        moneyViewer.setMoney(money);
        moneyViewer.show();
    }
    
    private Money readMoney() throws IOException{
        moneyDialog.execute();
        return moneyDialog.getMoney();
    }
    
    private Currency readCurrency() throws IOException{
        currencyDialog.execute();
        return currencyDialog.getCurrency();
    }
    
    private Date readDate() throws IOException, ParseException{
        dateDialog.execute();
        return dateDialog.getDate();
    }
}
