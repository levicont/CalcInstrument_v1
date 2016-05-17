/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Company {
private final SimpleIntegerProperty companyId;
private final SimpleStringProperty name;
private final SimpleStringProperty head;
private final SimpleStringProperty accountant;
    
public Company(int id, String name, String head, String accountant) {
this.companyId = new SimpleIntegerProperty(id);
this.name = new SimpleStringProperty (name);
this.head = new SimpleStringProperty (head);
this.accountant = new SimpleStringProperty (accountant);
}

    public SimpleIntegerProperty getCompanyId() {
        return companyId;
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public SimpleStringProperty getHead() {
        return head;
    }

    public SimpleStringProperty getAccountant() {
        return accountant;
    }


}

