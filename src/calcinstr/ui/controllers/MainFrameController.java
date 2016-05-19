/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.controllers;

import calcinstr.factories.ServiceFactory;
import calcinstr.services.CurrencyService;
import calcinstr.ui.entities.BankUI;
import calcinstr.ui.entities.CompanyUI;
import calcinstr.ui.entities.CurrencyUI;
import calcinstr.ui.entities.LoanUI;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;


/**
 * FXML Controller class
 *
 * @author victor
 */
public class MainFrameController implements Initializable {
    
    private static final Logger LOGGER = Logger.getLogger(MainFrameController.class);
    
    //Services
    private CurrencyService currencyService = ServiceFactory.getCurrencyService();
    
    
    //Convertors
    
    
    /*
    * Below we can see FXML components
    */
    
    //Loan components
    @FXML
    private TableView<LoanUI> tableLoanView;
    @FXML
    private TableColumn<LoanUI, Integer> colTableLoanID;    
    @FXML
    private TableColumn<LoanUI, String> colTableLoanCompany;
    @FXML
    private TableColumn<LoanUI,String> colTableLoanBank;
    @FXML
    private TableColumn<LoanUI,String> colTableLoanCurrency;
    @FXML
    private TableColumn<LoanUI,BigDecimal> colTableLoanAmont;
    @FXML
    private TableColumn<LoanUI,BigDecimal> colTableLoanRate;
    @FXML
    private TableColumn<LoanUI,String> colTableLoanStartDate;
    @FXML
    private TableColumn<LoanUI,String> colTableLoanEndDate;
    @FXML
    private TableColumn<LoanUI,String> colTableLoanType;
    
    @FXML
    private ComboBox<CompanyUI> cbLoanCompanyFilter;
    @FXML
    private ComboBox<BankUI> cbLoanBankFilter;
    @FXML
    private ComboBox<CurrencyUI> cbLoanCurrencyFilter;
    
    @FXML
    private ComboBox<CompanyUI> cbLoanCompany;
    @FXML
    private ComboBox<BankUI> cbLoanBank;
    @FXML
    private ComboBox<CurrencyUI> cbLoanCUrrency;
    @FXML
    private TextField txfLoanAmount;    
    @FXML
    private TextField txfLoanRate;
    @FXML
    private DatePicker dpLoanStartDate;
    @FXML
    private DatePicker dpLoanEndDate;
    @FXML
    private ComboBox<String> cbLoanType;
    
    @FXML
    private Button btAddLoan;
    @FXML
    private Button btUpdateLoan;
    @FXML
    private Button btSaveLoan;
    @FXML
    private Button btDeleteLoan;
    
    //Currency componets
    @FXML
    private TableView<CurrencyUI> tableCurrencyView;
    @FXML
    private TableColumn<CurrencyUI, Integer> colTableCurrencyID;    
    @FXML
    private TableColumn<CurrencyUI, String> colTableCurrencyCode;
    @FXML
    private TableColumn<CurrencyUI,String> colTableCurrencyName;
    
    @FXML
    private TextField txfCurrencyCode;    
    @FXML
    private TextField txfCurrencyName;
    
    @FXML
    private Button btAddCurrency;
    @FXML
    private Button btUpdateCurrency;
    @FXML
    private Button btSaveCurrency;
    @FXML
    private Button btDeleteCurrency;
    
    //Bank components    
    @FXML
    private TableView<BankUI> tableBankView;
    @FXML
    private TableColumn<BankUI, Integer> colTableBankID;   
    @FXML
    private TableColumn<BankUI,String> colTableBankName; 
    
    @FXML
    private TextField txfBankName;
    
    @FXML
    private Button btAddBank;
    @FXML
    private Button btUpdateBank;
    @FXML
    private Button btSaveBank;
    @FXML
    private Button btDeleteBank;
    
    //Company components
    @FXML
    private TableView<CompanyUI> tableCompanyView;
    @FXML
    private TableColumn<CompanyUI, Integer> colTableCompanyID;   
    @FXML
    private TableColumn<CompanyUI,String> colTableCompanyName; 
    @FXML
    private TableColumn<CompanyUI,String> colTableCompanyHead;
    @FXML
    private TableColumn<CompanyUI,String> colTableCompanyAccountant;
    
    @FXML
    private TextField txfCompanyName;
    @FXML
    private TextField txfCompanyHead;
    @FXML
    private TextField txfCompanyAccountant;
    
    @FXML
    private Button btAddCompany;
    @FXML
    private Button btUpdateCompany;
    @FXML
    private Button btSaveCompany;
    @FXML
    private Button btDeleteCompany;
    
    
    
            
    
    private ObservableList<CurrencyUI> currencyUIList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }    
    
    private void init(){
        initCurrencyTabPane();
    }
    
    private void initCurrencyTabPane(){
        initCurrencyTableView();
    }
    
    private void initCurrencyTableView(){
        
    }
    
}
