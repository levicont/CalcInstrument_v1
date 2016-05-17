/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lena
 */
public class Bank{
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    
public Bank (int id, String name ){
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty (name);
}
public SimpleIntegerProperty getId() {
return id;
}

public SimpleStringProperty getName() {
return name;
}

}
