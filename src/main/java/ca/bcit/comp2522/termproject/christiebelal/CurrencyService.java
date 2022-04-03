package ca.bcit.comp2522.termproject.christiebelal;

import com.almasb.fxgl.core.EngineService;
import com.almasb.fxgl.core.collection.PropertyMap;
import javafx.beans.property.IntegerProperty;

public class CurrencyService extends EngineService {
    private final PropertyMap map = new PropertyMap();

    public CurrencyService(){
        map.setValue("currency", 0);
    }

    public int getCurrency(){
        return map.getInt("currency");
    }

    public void setCurrency(int value){
        map.setValue("currency", value);
    }

    public IntegerProperty currencyProperty(){
        return map.intProperty("currency");
    }
}
