/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.controllers;

import calcinstr.config.R;
import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.factories.ConvertersFactory;
import calcinstr.factories.ServiceFactory;
import calcinstr.models.Bank;
import calcinstr.models.Company;
import calcinstr.models.Currency;
import calcinstr.models.Loan;
import calcinstr.services.BankService;
import calcinstr.services.CompanyService;
import calcinstr.services.CurrencyService;
import calcinstr.services.LoanService;
import calcinstr.ui.converters.BankConverter;
import calcinstr.ui.converters.CompanyConverter;
import calcinstr.ui.converters.CurrencyConverter;
import calcinstr.ui.converters.LoanConverter;
import calcinstr.ui.entities.BankUI;
import calcinstr.ui.entities.CompanyUI;
import calcinstr.ui.entities.CurrencyUI;
import calcinstr.ui.entities.LoanUI;
import calcinstr.util.ControlFXUtils;
import calcinstr.util.EventFXUtil;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private BankService bankService = ServiceFactory.getBankService();
    private CompanyService companyService = ServiceFactory.getCompanyService();
    private LoanService loanService = ServiceFactory.getLoanService();

    //Convertors
    private BankConverter bankConverter = ConvertersFactory.getBankConverter();
    private CurrencyConverter currencyConverter = ConvertersFactory.getCurrencyConverter();
    private CompanyConverter companyConverter = ConvertersFactory.getCompanyConverter();
    private LoanConverter loanConverter = ConvertersFactory.getLoanConverter();

    /*
    * Below we can see FXML components
     */
    //Main Pane
    @FXML
    private TabPane tabPaneMain;
    @FXML
    private Tab tabLoans;
    @FXML
    private Tab tabCurrensies;
    @FXML
    private Tab tabBanks;
    @FXML
    private Tab tabCompanies;
    @FXML
    private Tab tabCalculation;

    //Loan components
    @FXML
    private TableView<LoanUI> tableLoanView;
    @FXML
    private TableColumn<LoanUI, Integer> colTableLoanID;
    @FXML
    private TableColumn<LoanUI, String> colTableLoanCompany;
    @FXML
    private TableColumn<LoanUI, String> colTableLoanBank;
    @FXML
    private TableColumn<LoanUI, String> colTableLoanCurrency;
    @FXML
    private TableColumn<LoanUI, BigDecimal> colTableLoanAmount;
    @FXML
    private TableColumn<LoanUI, BigDecimal> colTableLoanRate;
    @FXML
    private TableColumn<LoanUI, String> colTableLoanStartDate;
    @FXML
    private TableColumn<LoanUI, String> colTableLoanEndDate;
    @FXML
    private TableColumn<LoanUI, String> colTableLoanType;

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
    private ComboBox<CurrencyUI> cbLoanCurrency;
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
    private TableColumn<CurrencyUI, String> colTableCurrencyName;

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
    private TableColumn<BankUI, String> colTableBankName;

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
    private TableColumn<CompanyUI, String> colTableCompanyName;
    @FXML
    private TableColumn<CompanyUI, String> colTableCompanyHead;
    @FXML
    private TableColumn<CompanyUI, String> colTableCompanyAccountant;

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

    private ObservableList<CurrencyUI> allCurrencyList = FXCollections.observableArrayList();
    private ObservableList<BankUI> allBankList = FXCollections.observableArrayList();
    private ObservableList<CompanyUI> allCompanyList = FXCollections.observableArrayList();
    private ObservableList<LoanUI> allLoanList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LOGGER.debug("INITIALIZING Properties Pane");
        init();
    }

    private void init() {
        initCurrencyTabPane();
        initBankTabPane();
        initCompanyTabPane();
        initLoanTabPane();
    }

    private void initLoanTabPane() {
        initLoanTableView();
        setDisabledLoanFields(true);
        TextFieldValidateListener validateListener = new TextFieldValidateListener();

        txfLoanAmount.textProperty().addListener(validateListener);
        txfLoanRate.textProperty().addListener(validateListener);
        dpLoanStartDate.valueProperty().addListener(validateListener);
        dpLoanEndDate.valueProperty().addListener(validateListener);
        cbLoanBank.valueProperty().addListener(validateListener);
        cbLoanCompany.valueProperty().addListener(validateListener);
        cbLoanCurrency.valueProperty().addListener(validateListener);
        cbLoanType.valueProperty().addListener(validateListener);

    }

    private void setDisabledLoanFields(boolean disable) {
        ControlFXUtils.setDisabledTextFields(disable, txfLoanAmount, txfLoanRate);
        ControlFXUtils.setDisabledComboBoxes(disable, cbLoanBank,
                cbLoanCurrency, cbLoanCompany, cbLoanType);
        ControlFXUtils.setDisabledDatePickers(disable, dpLoanStartDate,
                dpLoanEndDate);

    }

    private void initLoanTableView() {
        initAllLoanList();
        colTableLoanID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTableLoanCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colTableLoanBank.setCellValueFactory(new PropertyValueFactory<>("bank"));
        colTableLoanCurrency.setCellValueFactory(new PropertyValueFactory<>("currency"));
        colTableLoanAmount.setCellValueFactory(new PropertyValueFactory<>("amont"));
        colTableLoanRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        colTableLoanStartDate.setCellValueFactory(new PropertyValueFactory<>("startDateFormat"));
        colTableLoanEndDate.setCellValueFactory(new PropertyValueFactory<>("endDateFormat"));
        colTableLoanType.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableLoanView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableLoanView.addEventHandler(Event.ANY, new TableViewEventHandler());
        tableLoanView.setItems(allLoanList);
    }

    private void initAllLoanList() {
        allLoanList.clear();
        try {
            for (Loan loan : loanService.getAll()) {
                LoanUI c = new LoanUI(loan);
                allLoanList.add(c);
            }
            LOGGER.debug("Loaded " + allLoanList.size() + " records from loan table");
        } catch (CalcInstrumentException ex) {
            LOGGER.warn(R.Errors.SQL_DATA_IS_UNAVAILEABLE);
        }
    }

    //Init Company
    private void initCompanyTabPane() {
        initCompanyTableView();
        setDisabledCompanyFields(true);
        TextFieldValidateListener validateListener = new TextFieldValidateListener();
        txfCompanyName.textProperty().addListener(validateListener);
        txfCompanyHead.textProperty().addListener(validateListener);
        txfCompanyAccountant.textProperty().addListener(validateListener);
    }

    private void setDisabledCompanyFields(boolean disable) {
        ControlFXUtils.setDisabledTextFields(disable, txfCompanyName,
                txfCompanyHead, txfCompanyAccountant);
    }

    private void initCompanyTableView() {
        initAllCompanyList();
        colTableCompanyID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTableCompanyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTableCompanyHead.setCellValueFactory(new PropertyValueFactory<>("head"));
        colTableCompanyAccountant.setCellValueFactory(new PropertyValueFactory<>("accountant"));
        tableCompanyView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableCompanyView.addEventHandler(Event.ANY, new TableViewEventHandler());
        tableCompanyView.setItems(allCompanyList);
    }

    private void initAllCompanyList() {
        allCompanyList.clear();
        try {
            for (Company company : companyService.getAll()) {
                CompanyUI c = new CompanyUI(company);
                allCompanyList.add(c);
            }
            LOGGER.debug("Loaded " + allCompanyList.size() + " records from company table");
        } catch (CalcInstrumentException ex) {
            LOGGER.warn(R.Errors.SQL_DATA_IS_UNAVAILEABLE);
        }
    }

    // init Bank
    private void initBankTabPane() {
        initBankTableView();
        setDisabledBankFields(true);
        TextFieldValidateListener validateListener = new TextFieldValidateListener();
        txfBankName.textProperty().addListener(validateListener);
        btSaveBank.setDisable(true);
    }

    private void setDisabledBankFields(boolean disable) {
        ControlFXUtils.setDisabledTextFields(disable, txfBankName);
    }

    private void initBankTableView() {
        initAllBankList();
        colTableBankID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTableBankName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableBankView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableBankView.addEventHandler(Event.ANY, new TableViewEventHandler());
        tableBankView.setItems(allBankList);
    }

    private void initAllBankList() {
        allBankList.clear();
        try {
            for (Bank bank : bankService.getAll()) {
                BankUI c = new BankUI(bank);
                allBankList.add(c);

            }
            LOGGER.debug("Loaded " + allBankList.size() + " records from bank table");
        } catch (CalcInstrumentException ex) {
            LOGGER.warn(R.Errors.SQL_DATA_IS_UNAVAILEABLE);
        }
    }

    @FXML
    private void editBank() {
        if (tableBankView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        setDisabledBankFields(false);
        txfBankName.requestFocus();
    }

    @FXML
    private void addBank() {
        BankUI newRecordUI = new BankUI();
        allBankList.add(newRecordUI);
        tableBankView.getSelectionModel().clearSelection();
        tableBankView.getSelectionModel().select(newRecordUI);
        tableBankView.fireEvent(EventFXUtil.getMouseClickEvent());
        editBank();
    }

    @FXML
    private void saveBank() {
        setDisabledBankFields(true);
        btSaveBank.setDisable(true);
        BankUI updBank = tableBankView.getSelectionModel().getSelectedItem();
        updateBankFromFields(updBank);
        Bank bank = null;
        try {
            bank = bankConverter.convert(updBank);
            if (bank == null)
                return;
            if(bank.getId() != 0){
                bankService.update(bank);
                LOGGER.debug("SAVE Bank: bank updated in DB");
            }else{
                bankService.add(bank);
                LOGGER.debug("SAVE Bank: bank iserted in DB");
            }            

        } catch (CalcInstrumentException ex) {
          LOGGER.warn(R.Errors.SQL_ERROR + " message: "+ex.getMessage());
          initBankTabPane();
        }
    }

    private void updateBankFromFields(BankUI bankUI) {
        if (null == bankUI) {
            return;
        }
        bankUI.setName(txfBankName.getText().trim());
    }
    
    @FXML
    private void deleteBank(){
        Optional<ButtonType> choise = ControlFXUtils.getResponseDeleteDialog();
        if(choise.get() == ButtonType.OK){
            LOGGER.debug("OK CHOISED!");
        }else{
               LOGGER.debug("CANCEL CHOISED!");
        }     
    }

    // init Currency 
    private void initCurrencyTabPane() {
        initCurrencyTableView();
        setDisabledCurrencyFields(true);
        TextFieldValidateListener validateListener = new TextFieldValidateListener();
        txfCurrencyCode.textProperty().addListener(validateListener);
        txfCurrencyName.textProperty().addListener(validateListener);
    }

    private void setDisabledCurrencyFields(boolean disabled) {
        ControlFXUtils.setDisabledTextFields(disabled, txfCurrencyName, txfCurrencyCode);
    }

    private void initCurrencyTableView() {
        initAllCurrencyList();
        colTableCurrencyID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTableCurrencyCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colTableCurrencyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCurrencyView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableCurrencyView.addEventHandler(Event.ANY, new TableViewEventHandler());
        tableCurrencyView.setItems(allCurrencyList);
    }

    private void initAllCurrencyList() {
        allCurrencyList.clear();
        try {
            for (Currency curr : currencyService.getAll()) {
                CurrencyUI c = new CurrencyUI(curr);
                allCurrencyList.add(c);

            }
            LOGGER.debug("Loaded " + allCurrencyList.size() + " records from currency table");
        } catch (CalcInstrumentException ex) {
            LOGGER.warn(R.Errors.SQL_DATA_IS_UNAVAILEABLE);
        }
    }

    //Handlers and Listeners
    private class TableViewEventHandler implements EventHandler<javafx.event.Event> {

        @Override
        public void handle(javafx.event.Event event) {
            if (ControlFXUtils.isEventIsSelectedMouse(event)
                    || ControlFXUtils.isEventIsSelectedKeyOnList(event)) {

                if (event.getSource().getClass().equals(TableView.class)) {
                    TableView source = (TableView) event.getSource();
                    if (source.equals(tableBankView)) {
                        doSelectBank();
                        return;
                    }
                    if (source.equals(tableCompanyView)) {
                        //doSelectCommission();
                        return;
                    }
                    if (source.equals(tableCurrencyView)) {
                        //doSelectTeacher();
                        return;
                    }
                    if (source.equals(tableLoanView)) {
                        //doSelectNDTDocument();                        
                    }

                }

            }
        }
        
        private void doSelectBank(){
            BankUI selectedBank = tableBankView.getSelectionModel().getSelectedItem();
            if(selectedBank == null)
                return;
            setDisabledBankFields(true);
            txfBankName.setText(selectedBank.getName());
            btSaveBank.setDisable(true);
        }

    }

    private class TextFieldValidateListener implements InvalidationListener {

        @Override
        public void invalidated(Observable observable) {
            LOGGER.debug("VALIDATE LISTENER: invalidation in textField");
            readyToSave();
        }

        private void readyToSave() {
            Tab selectedTab = tabPaneMain.getSelectionModel().getSelectedItem();
            if (selectedTab == null) {
                return;
            }

            if (selectedTab.equals(tabLoans)) {
                if (txfLoanAmount.isEditable()) {
                    btSaveLoan.setDisable(false);
                    return;
                }
            }
            if (selectedTab.equals(tabCurrensies)) {
                if (txfCurrencyCode.isEditable()) {
                    btSaveCurrency.setDisable(false);
                    return;
                }
            }
            if (selectedTab.equals(tabBanks)) {
                if (txfBankName.isEditable()) {
                    btSaveBank.setDisable(false);
                    return;
                }
            }
            if (selectedTab.equals(tabCompanies)) {
                if (txfCompanyName.isEditable()) {
                    btSaveCompany.setDisable(false);
                    return;
                }
            }

        }
    }

}
