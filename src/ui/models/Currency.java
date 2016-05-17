/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author Lena
 */
public class Currency {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty code;
    private final SimpleStringProperty name;
    
public Currency (int id, String code, String name) {
    this.id = new SimpleIntegerProperty(id);
    this.code = new SimpleStringProperty (code);
    this.name = new SimpleStringProperty (name);
}
public SimpleIntegerProperty getId() {
return id;
}

public SimpleStringProperty  getCode() {
return code;
}

public SimpleStringProperty getName() {
return name;
}

}
