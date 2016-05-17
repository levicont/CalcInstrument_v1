/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lena
 */
public class BankProperty{
    private final SimpleIntegerProperty bankin;
    private final SimpleStringProperty bankname;
    
public BankProperty (int bankin, String bankname) {
    this.bankin = new SimpleIntegerProperty(bankin);
    this.bankname = new SimpleStringProperty (bankname);
}
public SimpleIntegerProperty getin() {
return bankin;
}

public SimpleStringProperty getname() {
return bankname;
}

}
