/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.entities;

import calcinstr.config.R;
import calcinstr.models.Company;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Victor
 */
public class CompanyUI {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty head = new SimpleStringProperty();
    private final SimpleStringProperty accountant = new SimpleStringProperty();
   
    public CompanyUI(Company company){
        id.set(company.getId());
        name.set(company.getName());
        head.set(company.getHead());
        accountant.set(company.getAccountant());
    }
    
    public CompanyUI(){
        id.set(R.ModelSettings.DEFAULT_ID);
        name.set(R.ModelSettings.EMPTY_STRING);
        head.set(R.ModelSettings.EMPTY_STRING);
        accountant.set(R.ModelSettings.EMPTY_STRING);
    }
    
    
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty headProperty() {
        return head;
    }

    public SimpleStringProperty accountantProperty() {
        return accountant;
    }
    
    //Getters
    public int getId(){
        return id.get();
    }
    
    public String getName(){
        return name.get();
    }
    
    public String getHead(){
        return head.get();
    }
    
    public String getAccountant(){
        return accountant.get();
    }
    
    //Setters
    public void setId(int id){
        this.id.set(id);
    }
    
    public void setName(String name){
        this.name.set(name);
    }
    
    public void setHead(String head){
        this.head.set(head);
    }
    
    public void setAccountant(String accountant){
        this.accountant.set(accountant);
    }

    @Override
    public String toString() {
        return name.get();
    }
    
    


}
