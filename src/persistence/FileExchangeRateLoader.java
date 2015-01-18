package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import model.Currency;
import model.ExchangeRate;

public class FileExchangeRateLoader implements ExchangeRateLoader{
    
    private static FileExchangeRateLoader instance;
     
    private FileExchangeRateLoader(){
    }
    
    public static FileExchangeRateLoader getInstance() {
        if (instance == null)
            instance = new FileExchangeRateLoader();
        return instance;
    }
    
    public static ArrayList<String> load(String filename){
        ArrayList<String> list=new ArrayList<>();
        try {
            BufferedReader reader=new BufferedReader(new FileReader(new File(filename)));
            while(true){
                String mail=reader.readLine();
                if(mail==null)break;
                if(!mail.contains("@"))continue;
                list.add(mail.split("@")[1].toLowerCase());
            }
        }
        catch (FileNotFoundException ex) {
        }
        catch (IOException ex) {
        }
        return list;
    }

    @Override
    public ExchangeRate load(Currency fromCurrency, Currency toCurrency) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ExchangeRate load(Date date, Currency fromCurrency, Currency toCurrency) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
