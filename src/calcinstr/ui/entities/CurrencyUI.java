/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.entities;

import calcinstr.config.R;
import calcinstr.models.Currency;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Victor
 */
public class CurrencyUI {

    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty code = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();

    public CurrencyUI(Currency currency) {
        id.set(currency.getId());
        code.set(currency.getCode());
        name.set(currency.getName());
    }

    public CurrencyUI() {
        id.set(R.ModelSettings.DEFAULT_ID);
        code.set(R.ModelSettings.EMPTY_STRING);
        name.set(R.ModelSettings.EMPTY_STRING);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    //Getters
    public int getId(){
        return id.get();
    }
    
    public String getName(){
        return name.get();
    }
    
    public String getCode(){
        return code.get();
    }
    
    //Setters
    public void setId(int id){
        this.id.set(id);
    }
    
    public void setName(String name){
        this.name.set(name);
    }
    
    public void setCode(String code){
        this.code.set(code);
    }

    @Override
    public String toString() {
        return name.get() + "(" + code.get() + ')';
    }
    
    
}
