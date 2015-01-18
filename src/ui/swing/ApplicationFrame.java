package ui.swing;

import control.command.CommandActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApplicationFrame extends JFrame{
    
    private final CommandActionListener actionListener;
    private MoneyPanel moneyPanel;
    private DatePanel datePanel;
    private MoneyViewerPanel moneyViewer;
    private CurrencyPanel currencyPanel;
    
    public ApplicationFrame(CommandActionListener actionListener){
        this.actionListener=actionListener;
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }

    private void createComponents() {
        this.add(createPanel());
        this.add(createToolbar(), BorderLayout.SOUTH);
    }

    private JPanel createToolbar() {
        JPanel panel=new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(createCalculateButton());
        panel.add(createCancelButton());
        return panel;
    }

    private JPanel createPanel() {
        JPanel principalPanel=new JPanel(new BorderLayout());
        principalPanel.add(firstPanel(), BorderLayout.NORTH);
        principalPanel.add(SecondPanel(), BorderLayout.CENTER);
        return principalPanel;
    }

    private JPanel firstPanel() {
        JPanel panelMoneyExchange=new JPanel();
        panelMoneyExchange.add(createMoneyPanel());
        panelMoneyExchange.add(new JLabel("to:"));
        panelMoneyExchange.add(createCurrencyPanel());
        return panelMoneyExchange;
    }

    private JPanel SecondPanel() {
        JPanel panelDateAndViewer=new JPanel();
        panelDateAndViewer.add(new JLabel("Date:"));
        panelDateAndViewer.add(createDatePanel());
        panelDateAndViewer.add(new JLabel("Result:"));
        panelDateAndViewer.add(createMoneyViewerPanel());
        return panelDateAndViewer;
    }

    private JPanel createMoneyPanel() {
        MoneyPanel panel=new MoneyPanel();
        moneyPanel=panel;
        panel.show();
        return panel;
    }

    private JPanel createDatePanel() {
        DatePanel panel=new DatePanel();
        datePanel=panel;
        panel.show();
        return panel;
    }

    private JPanel createMoneyViewerPanel() {
        MoneyViewerPanel panel=new MoneyViewerPanel();
        moneyViewer=panel;
        panel.show();
        return panel;
    }

    private JPanel createCurrencyPanel() {
        CurrencyPanel panel=new CurrencyPanel();
        currencyPanel=panel;
        panel.show();
        return panel;
    }

    private JButton createCalculateButton() {
        JButton calculate=new JButton("Calculate");
        calculate.addActionListener(actionListener.getActionListener("calculate"));
        return calculate;
    }

    private JButton createCancelButton() {
        JButton cancel=new JButton("Exit");
        cancel.addActionListener(actionListener.getActionListener("exit"));
        return cancel;
    }

    public MoneyPanel getMoneyPanel() {
        return moneyPanel;
    }

    public DatePanel getDatePanel() {
        return datePanel;
    }

    public MoneyViewerPanel getMoneyViewer() {
        return moneyViewer;
    }

    public CurrencyPanel getCurrencyPanel() {
        return currencyPanel;
    }
}