package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.Currency;
import model.ExchangeRate;
import model.Number;
import oracle.jdbc.OracleDriver;

    
public class DBExchangeRateLoader implements ExchangeRateLoader {

    private static DBExchangeRateLoader instance;

    private DBExchangeRateLoader() {
    }

    public static DBExchangeRateLoader getInstance() {
        if (instance == null) {
            instance = new DBExchangeRateLoader();
        }
        return instance;
    }

    @Override

    public ExchangeRate load(Currency fromCurrency, Currency toCurrency) {
        return load(new Date(), fromCurrency, toCurrency);
    }

    @Override
    public ExchangeRate load(Date date, Currency fromCurrency, Currency toCurrency) {
        
        try {
            Connection connection = getConnection();
            return new ExchangeRate(date, fromCurrency, toCurrency, new Number(getExchangeRateFromDB(toCurrency, fromCurrency, connection, date)));
        }
        catch (SQLException ex) {
        }
        return null;        
    }

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String username = "system";
        String password = "orcl";
        String url = "jdbc:oracle:thin:@" + "localhost:101:orcl";
        return DriverManager.getConnection(url, username, password);
    }
    
    private double getExchangeRateFromDB(Currency toCurrency, Currency fromCurrency, Connection connection, Date date) {
        try {
            String query = "select cambio from historico_cambios "
                    + "where divisa_desde='" + fromCurrency.getCode() + "' and divisa_a='" + toCurrency.getCode() + "'";
            ResultSet resulSet;
            resulSet = connection.createStatement().executeQuery(query);
            while (resulSet.next()) 
                return resulSet.getDouble(1);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return 0;
    }
}
