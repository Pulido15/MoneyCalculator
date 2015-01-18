package main;

import control.ExchangeMoneyControl;
import control.command.Command;
import control.command.CommandActionListener;
import control.command.CommandCalculate;
import control.command.CommandRegister;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import persistence.DBExchangeRateLoader;
import persistence.ExchangeRateLoader;
import persistence.FileCurrencySetLoader;
import ui.console.ConsoleCurrencyDialog;
import ui.console.ConsoleDateDialog;
import ui.console.ConsoleMoneyDialog;
import ui.console.ConsoleMoneyViewer;
import ui.swing.ApplicationFrame;

public class Main{
    
    
    private final static String PATH = "C:\\Users\\Puli\\Documents\\NetBeansProjects\\MoneyCalculatorIS2\\src\\lib\\currencies.txt";
    
    public static void main(String[] args) throws IOException, ParseException {
        executeSwing();
    }

    public static void executeConsole() throws IOException, ParseException {
        FileCurrencySetLoader fileCurrencySetLoader = FileCurrencySetLoader.getInstance();
        fileCurrencySetLoader.setFilename(PATH);
        fileCurrencySetLoader.load();
        ConsoleMoneyDialog moneyDialog = new ConsoleMoneyDialog();
        ConsoleCurrencyDialog currencyDialog = new ConsoleCurrencyDialog();
        ConsoleDateDialog dateDialog = new ConsoleDateDialog();
        ConsoleMoneyViewer moneyViewer = new ConsoleMoneyViewer();
        ExchangeRateLoader exchangeRateLoader = DBExchangeRateLoader.getInstance();
        ExchangeMoneyControl exchangeMoneyControl = new ExchangeMoneyControl(moneyDialog, currencyDialog, moneyViewer, exchangeRateLoader, dateDialog);
        exchangeMoneyControl.execute();
    }
    
     private static void executeSwing() {
        FileCurrencySetLoader fileCurrencySetLoader = FileCurrencySetLoader.getInstance();
        fileCurrencySetLoader.setFilename(PATH);
        fileCurrencySetLoader.load();
        final CommandRegister commandRegister = new CommandRegister();
        CommandActionListener actionListener = new CommandActionListener() {

            @Override
            public ActionListener getActionListener(final String action) {
                return new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        commandRegister.getActionMap(action).execute();
                    }
                };
            }
        };
        
        final ApplicationFrame frame = new ApplicationFrame(actionListener);
        
        commandRegister.setActionMap("calculate", new CommandCalculate(
                frame.getMoneyPanel(),
                frame.getCurrencyPanel(),
                frame.getDatePanel(),
                frame.getMoneyViewer()));

        commandRegister.setActionMap("exit", new Command() {

            @Override
            public void execute() {
                frame.dispose();
            }
        });
    }
}