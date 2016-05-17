/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static java.lang.System.in;
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
    private TableView <CurrencyProperty> tableCurrency;
    
    @FXML
    private TableColumn <CurrencyProperty, Integer> inCurrency;
    @FXML
    private TableColumn <CurrencyProperty, String> codeCurrency;
    @FXML
    private TableColumn <CurrencyProperty, String> nameCurrency;
    
    ObservableList<CurrencyProperty> CurrencyData = FXCollections.observableArrayList(
    new CurrencyProperty (1,"UAH","гривна"),
    new CurrencyProperty (2,"USD","доллар США"));

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    inCurrency.setCellValueFactory(new PropertyValueFactory <CurrencyProperty, Integer>("inCurrency"));//в кавычках может быть неправильно
    codeCurrency.setCellValueFactory(new PropertyValueFactory <CurrencyProperty, String>("codeCurrency"));
    nameCurrency.setCellValueFactory(new PropertyValueFactory <CurrencyProperty, String>("nameCurrency"));
    
       }    
    
}
