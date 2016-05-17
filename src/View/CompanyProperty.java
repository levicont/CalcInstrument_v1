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

public class CompanyProperty {
private final SimpleIntegerProperty companyin;
private final SimpleStringProperty companyname;
private final SimpleStringProperty companyhead;
private final SimpleStringProperty companyaccountant;
    
public CompanyProperty(int companyin, String companyname, String companyhead, String companyaccountant) {
this.companyin = new SimpleIntegerProperty(companyin);
this.companyname = new SimpleStringProperty (companyname);
this.companyhead = new SimpleStringProperty (companyhead);
this.companyaccountant = new SimpleStringProperty (companyaccountant);
}

public SimpleIntegerProperty getin() {
return companyin;
}

public SimpleStringProperty getname() {
return companyname;
}
public SimpleStringProperty gethead() {
return companyhead;
}

public SimpleStringProperty getaccountant() {
return companyaccountant;
}
}

