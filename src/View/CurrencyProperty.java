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
public class CurrencyProperty {
    private final SimpleIntegerProperty in;
    private final SimpleStringProperty code;
    private final SimpleStringProperty name;
    
public CurrencyProperty (int in, String code, String name) {
    this.in = new SimpleIntegerProperty(in);
    this.code = new SimpleStringProperty (code);
    this.name = new SimpleStringProperty (name);
}
public SimpleIntegerProperty getin() {
return in;
}

public SimpleStringProperty  getcode() {
return code;
}

public SimpleStringProperty getname() {
return name;
}

}
