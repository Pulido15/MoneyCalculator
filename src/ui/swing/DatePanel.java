package ui.swing;

import com.toedter.calendar.JDateChooser;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.JPanel;
import ui.DateDialog;

public class DatePanel extends JPanel implements DateDialog{
    private Date date;

    public DatePanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        date=new Date();
    }
    
    @Override
    public void show(){
        this.add(createDateChooser());
    }

    private JDateChooser createDateChooser() {
        JDateChooser dateChooser=new JDateChooser();
        dateChooser.setDate(new Date());
        dateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if(date.toString().equals(e.getPropertyName()))
                    date=(Date)e.getNewValue();
            }
        });
        return dateChooser;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
    }
}