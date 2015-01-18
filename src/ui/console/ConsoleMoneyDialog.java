package ui.console;

import ui.console.ConsoleCurrencyDialog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.Currency;
import model.Money;
import model.Number;

public class ConsoleMoneyDialog {

    private Money money;

    public Money execute() throws IOException {
        Currency currency;
        Number amount = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean gotCorrect = false;
        while(!gotCorrect){
            try{
                System.out.println("Introduzca una cantidad de dinero");
                amount = new Number(Double.parseDouble(reader.readLine()));
                gotCorrect = true;
            }catch(Exception e){
                System.out.println();
                System.out.println("Error: no se ha introducido una cantidad de dinero");
                System.out.println();
                continue;
            }
        }
        ConsoleCurrencyDialog currencyDialog = new ConsoleCurrencyDialog();
        currency = currencyDialog.execute();
        money = new Money(amount, currency);
        return money;
    }

    public Money getMoney() {
        return money;
    }
}
