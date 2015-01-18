package control.command;

import model.Currency;
import model.ExchangeRate;
import model.Money;
import model.MoneyExchanger;
import persistence.DBExchangeRateLoader;
import ui.CurrencyDialog;
import ui.MoneyDialog;
import ui.MoneyViewer;
import ui.swing.CurrencyPanel;
import ui.swing.DatePanel;
import ui.swing.MoneyPanel;
import ui.swing.MoneyViewerPanel;

public class CommandCalculate extends Command{
    
    private final MoneyViewer moneyViewer;
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final DatePanel dateDialog;

    public CommandCalculate(MoneyPanel moneyPanel, CurrencyPanel currencyPanel, DatePanel datePanel, MoneyViewerPanel moneyViewer) {
        this.moneyDialog = moneyPanel;
        this.currencyDialog = currencyPanel;
        this.dateDialog = datePanel;
        this.moneyViewer = moneyViewer;
    }
    
    @Override
    public void execute(){
        try{
            Money money = moneyDialog.getMoney();
            Currency currency = currencyDialog.getCurrency();
            ExchangeRate exchangeRate = DBExchangeRateLoader.getInstance().load(money.getCurrency(), currency);
            moneyViewer.setMoney(MoneyExchanger.exchange(money, exchangeRate));
            moneyViewer.show();
        }
        catch (Exception e){
        }
    }
}