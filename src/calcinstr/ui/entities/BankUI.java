/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.entities;

import calcinstr.config.R;
import calcinstr.models.Bank;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Victor
 */
public class BankUI {
    
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    
    public BankUI(Bank bank){       
        id.set(bank.getId());
        name.set(bank.getName());        
    }
    
    public BankUI(){
        id.set(R.ModelSettings.DEFAULT_ID);
        name.set(R.ModelSettings.EMPTY_STRING);
    }

    public int getId() {
        return id.get();
    }
    
    public void setId(int id){
        this.id.set(id);
    }
    
    public SimpleIntegerProperty idProperty(){
        return id;
    }

    public String getName() {
        return name.get();
    }
    
    public void setName(String name){
        this.name.set(name);
    }
    
    public SimpleStringProperty nameProperty(){
        return name;
    }

    @Override
    public String toString() {
        return name.get();
    }
    
    
    
}
