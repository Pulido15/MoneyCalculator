package ui.swing;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.CurrencySet;
import model.Money;
import model.Number;
import ui.CurrencyDialog;
import ui.MoneyDialog;

public class MoneyPanel extends JPanel implements MoneyDialog{
    private String amount;
    private CurrencyDialog currencyDialog;

    public MoneyPanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(createTextField());
        this.add(new JLabel("from:"));
        this.add(createCurrencyPanel());
    }

    private JTextField createTextField() {
        final JTextField textField=new JTextField(10);
        textField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                amount=textField.getText();
            }
        });
        return textField;
    }

    @Override
    public Money getMoney() {
        if(amount == null) amount = "0";
        return new Money(new Number(Double.parseDouble(amount)), currencyDialog.getCurrency());
    }

    @Override
    public void setMoney(Money money) {
    }

    private CurrencyPanel createCurrencyPanel() {
        CurrencyPanel panel = new CurrencyPanel();
        panel.getComboCurrencies().setSelectedItem("EUR- Euro");
        panel.setCurrency(CurrencySet.getInstance().get("EUR"));
        currencyDialog = panel;
        return panel;
    }
}