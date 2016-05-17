/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controllers;

import ui.models.Currency;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Lena
 */
public class CurrencyViewController implements Initializable {

    @FXML
    private TableView <Currency> tableCurrency;
    
    @FXML
    private TableColumn <Currency, Integer> inCurrency;
    @FXML
    private TableColumn <Currency, String> codeCurrency;
    @FXML
    private TableColumn <Currency, String> nameCurrency;
    
    ObservableList<Currency> currencyData = FXCollections.observableArrayList(new Currency (1,"UAH","гривна"),
    new Currency (2,"USD","доллар США"));

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    inCurrency.setCellValueFactory(new PropertyValueFactory <Currency, Integer>("id"));//в кавычках может быть неправильно
    codeCurrency.setCellValueFactory(new PropertyValueFactory <Currency, String>("code"));
    nameCurrency.setCellValueFactory(new PropertyValueFactory <Currency, String>("name"));
    
    tableCurrency.setItems(currencyData);
    
       }    
    
}
