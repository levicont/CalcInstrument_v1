/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.controllers;

import calcinstr.calc.LoanCalculator;
import calcinstr.config.R;
import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.factories.ConvertersFactory;
import calcinstr.factories.LoanCalculatorFactory;
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
import calcinstr.util.DateUtil;
import calcinstr.util.EventFXUtil;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.Label;
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
public class MainFrameController implements Initializable, R {

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
    private ComboBox<String> cbLoanCompanyFilter;
    @FXML
    private ComboBox<String> cbLoanBankFilter;
    @FXML
    private ComboBox<String> cbLoanCurrencyFilter;

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
    @FXML
    private Button btCalculate;
    
    
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
    
    //Calculator components
    @FXML
    private Label lbCalcCompany;
    @FXML
    private Label lbCalcBank;
    @FXML
    private Label lbCalcCurrency;
    @FXML
    private Label lbCalcLoanType;
    @FXML
    private Label lbCalcLoanStartDate;
    @FXML
    private Label lbCalcLoanEndDate;
    @FXML
    private Label lbCalcLoanAmount;
    @FXML
    private Label lbCalcLoanRate;
    
    @FXML
    private TextField txfCalcSumRepayment;
    @FXML
    private DatePicker dpCalcDateMonthRepayment;
    @FXML
    private DatePicker dpCalcStartPeriod;
    @FXML
    private DatePicker dpCalcEndPeriod;
    @FXML
    private Button btCalcAmountForPeriod;
    @FXML
    private Label lbCalcAmountForPeriod;
    
            
    
    //Status flags
    private SimpleBooleanProperty isUnderEditingProp = new SimpleBooleanProperty(false);

    private ObservableList<CurrencyUI> allCurrencyList = FXCollections.observableArrayList();
    private ObservableList<String> allCurrencyFilterNameList =FXCollections.observableArrayList();
    
    private ObservableList<BankUI> allBankList = FXCollections.observableArrayList();
    private ObservableList<String> allBankFilterNameList =FXCollections.observableArrayList();
    
    private ObservableList<CompanyUI> allCompanyList = FXCollections.observableArrayList();
    private ObservableList<String> allCompanyFilterNameList = FXCollections.observableArrayList();
    
    private ObservableList<LoanUI> allLoanList = FXCollections.observableArrayList();
    private ObservableList<String> allLoanTypes = 
                FXCollections.observableArrayList(R.ModelSettings.LOAN_TYPES.values());

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
        initFlagProperties();
    }
    
    //-- Init FlagProperties
    
    private void initFlagProperties(){
        isUnderEditingProp.set(false);
        isUnderEditingProp.addListener(new UnderEditingValidateListaner());
        
    }
    
    
    //-- Init Loan
    private void initLoanTabPane() {
        initLoanTableView();
        initLoanComboBoxes();
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
        btSaveLoan.setDisable(true);
        isUnderEditingProp.set(false);
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
        colTableLoanAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
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
    
    private void initLoanComboBoxes(){
        cbLoanBank.setItems(allBankList);        
        cbLoanCompany.setItems(allCompanyList);       
        cbLoanCurrency.setItems(allCurrencyList);        
        cbLoanType.setItems(allLoanTypes);
        initLoanFilterComboBoxes();
    }
    private void initLoanFilterComboBoxes(){
        cbLoanCompanyFilter.setItems(allCompanyFilterNameList);        
        cbLoanBankFilter.setItems(allBankFilterNameList);
        cbLoanCurrencyFilter.setItems(allCurrencyFilterNameList);
        cbLoanCompanyFilter.getSelectionModel().selectFirst();
        cbLoanBankFilter.getSelectionModel().selectFirst();
        cbLoanCurrencyFilter.getSelectionModel().selectFirst();
        
        FilterLoanValidateListener listener = new FilterLoanValidateListener();
        cbLoanBankFilter.valueProperty().addListener(listener);
        cbLoanCompanyFilter.valueProperty().addListener(listener);
        cbLoanCurrencyFilter.valueProperty().addListener(listener);
        
    }

    @FXML
    private void editLoan() {
        if (tableLoanView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        setDisabledLoanFields(false);
         isUnderEditingProp.set(true);
        cbLoanCompany.requestFocus();
    }

    @FXML
    private void addLoan() {
        if(allBankList.isEmpty() || allCompanyList.isEmpty() 
                || allCurrencyList.isEmpty() ){
            ControlFXUtils.showWarningDialog("Невозможно добавить кредит \n Неполная база данных.");
            return;
        }
            
        LoanUI newRecordUI = new LoanUI();       
        allLoanList.add(newRecordUI);
        tableLoanView.getSelectionModel().clearSelection();
        tableLoanView.getSelectionModel().select(newRecordUI);
        tableLoanView.fireEvent(EventFXUtil.getMouseClickEvent());        
        editLoan();
    }

    @FXML
    private void saveLoan() {
        setDisabledLoanFields(true);
        btSaveLoan.setDisable(true);
        LoanUI updLoan = tableLoanView.getSelectionModel().getSelectedItem();
        Loan loan = null;
        try {
            updateLoanFromFields(updLoan);
            loan = loanConverter.convert(updLoan);
            if (loan == null) {
                return;
            }
            if (loan.getId() != 0) {
                loanService.update(loan);
                LOGGER.debug("SAVE Loan: loan updated in DB");
            } else {
                int id = loanService.add(loan);
                loan.setId(id);
                LOGGER.debug("SAVE Loan: loan iserted in DB");
            }

        } catch (CalcInstrumentException ex) {
            LOGGER.warn(R.Errors.SQL_ERROR + " message: " + ex.getMessage());
            ControlFXUtils.showErrorDialog(R.Errors.SAVE_ITEM_ERROR+"\n"+
                    ex.getMessage());
        } finally {
            initLoanTabPane();
            if(loan==null)
                return;
            LoanUI selLoan = selectLoanUIItemById(loan.getId());
            if(selLoan != null)
                ControlFXUtils.selectTableItem(tableLoanView, selLoan);
        }
    }

    private void updateLoanFromFields(LoanUI loanUI)throws CalcInstrumentException {
        if (null == loanUI) {
            return;
        }
        checkLoanFields();
        loanUI.setCompany(cbLoanCompany.getValue());
        loanUI.setBank(cbLoanBank.getValue());
        loanUI.setCurrency(cbLoanCurrency.getValue());
        loanUI.setAmount(BigDecimal.valueOf(Double.parseDouble(txfLoanAmount.getText().trim())));
        loanUI.setRate(BigDecimal.valueOf(Double.parseDouble(txfLoanRate.getText().trim())));
        loanUI.setStartDate(DateUtil.getDate(dpLoanStartDate.getValue()));
        loanUI.setEndDate(DateUtil.getDate(dpLoanEndDate.getValue()));
        loanUI.setType(cbLoanType.getValue());
    }
    
    private void checkLoanFields()throws CalcInstrumentException{
        if(cbLoanBank.getSelectionModel().getSelectedItem() == null)
            throw new CalcInstrumentException("Банк не выбран");
        if(cbLoanCompany.getSelectionModel().getSelectedItem() == null)
            throw new CalcInstrumentException("Компания не выбрана");
        if(cbLoanCurrency.getSelectionModel().getSelectedItem() == null)
            throw new CalcInstrumentException("Валюта не выбрана");
        if(cbLoanType.getSelectionModel().getSelectedItem() == null)
            throw new CalcInstrumentException("Тип не выбран");
        try{
            BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(txfLoanAmount.getText().trim()));
            if (amount.doubleValue()<=0)
                throw new CalcInstrumentException("Неверный формат числа суммы должна быть больше 0");
                        
        }catch(NumberFormatException ex){
            throw new CalcInstrumentException("Неверный формат числа суммы");
        }
        try{
            BigDecimal rate = BigDecimal.valueOf(Double.parseDouble(txfLoanRate.getText().trim()));
            if (rate.doubleValue()< 0)
                throw new CalcInstrumentException("Неверный формат числа ставки должна быть положительной");
            
        }catch(NumberFormatException ex){
            throw new CalcInstrumentException("Неверный формат числа ставки");
        }
        LocalDate startDate = dpLoanStartDate.getValue();
        LocalDate endDate = dpLoanEndDate.getValue();
        if(startDate == null)
            throw new CalcInstrumentException("Неверный формат даты открытия кредита");
        if(endDate == null)
            throw new CalcInstrumentException("Неверный формат даты окончания кредита");
        if(startDate.isAfter(endDate))
            throw new CalcInstrumentException("Окончание кредита не может быть раньше открытия");
        
        
    }

    private LoanUI selectLoanUIItemById(int id) {
        LoanUI result = null;
        for (LoanUI loanUI : tableLoanView.getItems()) {
            if (id == loanUI.getId()) {
                return loanUI;
            }
        }
        return result;
    }

    @FXML
    private void deleteLoan() {
        LoanUI loanUI = tableLoanView.getSelectionModel().getSelectedItem();
        if (loanUI == null) {
            return;
        }
        try {
            Loan loan = loanConverter.convert(loanUI);
            if (loan.getId()==0)
                return;
            
            Optional<ButtonType> choise = ControlFXUtils.getResponseDeleteDialog();
            if (choise.get() == ButtonType.OK) {
                LOGGER.debug("OK CHOISED!");
                loanService.delete(loan);
                initLoanTabPane();
                ControlFXUtils.selectFirstTableRecord(tableLoanView);
                LOGGER.info("Loan has been deleted");
            } 
        } catch (CalcInstrumentException ex) {
            LOGGER.warn("It is not possible delete Loan from DB message: "+ex.getMessage());
            ControlFXUtils.showErrorDialog(R.Errors.DELETE_ITEM_ERROR);
            initLoanTabPane();
            ControlFXUtils.selectFirstTableRecord(tableLoanView);
                
        }
    }

    //Calculating
    @FXML
    private void openCalcLoanTab(){
        LoanUI selectedLoan = tableLoanView.getSelectionModel().getSelectedItem();
        if(null == selectedLoan || selectedLoan.getId()<=0){
            ControlFXUtils.showWarningDialog("Кредит не выбран, или не сохранен в БД");
            return;
        }
        tabPaneMain.getSelectionModel().select(tabCalculation);
         
        initCalcTab(selectedLoan);
    }
    
    private void initCalcTab(LoanUI selectedLoan){
        lbCalcCompany.setText(selectedLoan.getCompany().toString());
        lbCalcBank.setText(selectedLoan.getBank().toString());
        lbCalcCurrency.setText(selectedLoan.getCurrency().toString());
        lbCalcLoanType.setText(selectedLoan.getType());
        lbCalcLoanStartDate.setText(selectedLoan.getStartDateFormat());
        lbCalcLoanEndDate.setText(selectedLoan.getEndDateFormat());
        lbCalcLoanAmount.setText(selectedLoan.getAmount().toPlainString());
        lbCalcLoanRate.setText(selectedLoan.getRate().toPlainString());     
        initCalcInputComponents(selectedLoan);
        
        
    }
    
    private void initCalcInputComponents(LoanUI selectedLoan){
        txfCalcSumRepayment.clear();
        dpCalcDateMonthRepayment.setValue(null);
        dpCalcStartPeriod.setValue(null);
        dpCalcEndPeriod.setValue(null);
        lbCalcAmountForPeriod.setText("---");
                
        if( selectedLoan.getType().equals(ModelSettings.LOAN_TYPES.get(
                ModelSettings.MOUNTH_LOAN_TYPE_KEY))){
            txfCalcSumRepayment.setDisable(false);
            dpCalcDateMonthRepayment.setDisable(false);
        }else{
            txfCalcSumRepayment.setDisable(true);
            dpCalcDateMonthRepayment.setDisable(true);
        }
    }
    
    @FXML
    private void calculateLoan(){
        LoanUI selectedLoan = tableLoanView.getSelectionModel().getSelectedItem();
        if(null == selectedLoan || selectedLoan.getId()<=0){
            ControlFXUtils.showWarningDialog("Кредит не выбран, или не сохранен в БД");
            return;
        }
        
        String loanType = selectedLoan.getType();
        LoanCalculator calculator = null;
        BigDecimal result = null;
        try{
            Loan loan = loanConverter.convert(selectedLoan);
            checkCalcFields(selectedLoan.getType());
            LocalDate Dbp = dpCalcStartPeriod.getValue();
            LocalDate Dep = dpCalcEndPeriod.getValue();
            
            if (loanType.equals(ModelSettings.LOAN_TYPES.get(ModelSettings.MOUNTH_LOAN_TYPE_KEY))){
                String amountRepaymentStr = txfCalcSumRepayment.getText().trim();
                BigDecimal amountRepayment = BigDecimal.valueOf(Double.parseDouble(amountRepaymentStr));
                calculator = LoanCalculatorFactory.getLoanMounthCalculator(loan, 
                        amountRepayment, dpCalcDateMonthRepayment.getValue());
                
            }else{
                calculator = LoanCalculatorFactory.getLoanYearCalculator(loan);
                
            }
            result = calculator.calculateAmountForPeriod(Dbp, Dep);
            lbCalcAmountForPeriod.setText(result.toPlainString());
            LOGGER.info("Result calculating = "+ result.toPlainString());
        }catch(CalcInstrumentException ex){
            LOGGER.warn("Incorrect input DATA "+ ex.getMessage());
            ControlFXUtils.showErrorDialog("Неверные исходные данные \n"+ex.getMessage());
        }
    }
    
/*  
	Дата выдачи(Db)
	Дата окончания(De)
        Сумма ежемесячного погашения(Sm)
        Дата ежемесячного погашения	(D)
        Дата начала периода расчета(Dbp)
        Дата окончания периода расчета (Dep) 
*/
    private void checkCalcFields(String loanType)throws CalcInstrumentException{
        LoanUI selectedLoan = tableLoanView.getSelectionModel().getSelectedItem();
        LocalDate Db = DateUtil.getLocalDate(selectedLoan.getStartDate());
        LocalDate De = DateUtil.getLocalDate(selectedLoan.getEndDate());        
        LocalDate Dbp = dpCalcStartPeriod.getValue();
        LocalDate Dep = dpCalcEndPeriod.getValue();
        
        if(DateUtil.getDifferenceDays(Dbp, Db) <= 0 ||
                DateUtil.getDifferenceDays(De, Dbp) <= 0 ||
                DateUtil.getDifferenceDays(Dep, Dbp) <= 0)
            throw new CalcInstrumentException("Недопустимое значение границы периода");
        
        if(DateUtil.getDifferenceDays(Dep, Db) <= 0 ||
                DateUtil.getDifferenceDays(De, Dep) <= 0)
            throw new CalcInstrumentException("Недопустимое значение даты окончания периода");
        
        
        if(loanType.equals(ModelSettings.LOAN_TYPES.get(ModelSettings.MOUNTH_LOAN_TYPE_KEY))){
            String sumRepaymentStr = txfCalcSumRepayment.getText().trim();
            if(sumRepaymentStr.isEmpty())
                throw new CalcInstrumentException("Поле сумма погашения не должно быть пустым");
            try{
                Double amount = Double.parseDouble(sumRepaymentStr);
                if(amount < 0)
                    throw new CalcInstrumentException("Поле сумма погашения должно положительным");
            }catch(NumberFormatException ex){
                    throw new CalcInstrumentException("Неверный формат числа");
            }
            
            LocalDate D = dpCalcDateMonthRepayment.getValue();
            long diffD_Dbp = DateUtil.getDifferenceDays(D, Dbp);
            if( diffD_Dbp <=0 )
                throw new CalcInstrumentException("Недопустимое значение даты погашения");
            
            long diffDep_D = DateUtil.getDifferenceDays(Dep, D);
            if( diffDep_D <= 0 )
                throw new CalcInstrumentException("Недопустимое значение даты погашения");
                        
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
        btSaveCompany.setDisable(true);
        isUnderEditingProp.set(false);
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
        allCompanyFilterNameList.clear();
        allCompanyFilterNameList.add(R.ModelSettings.ALL_COMPANY_FILTER_NAME);
        try {
            for (Company company : companyService.getAll()) {
                CompanyUI c = new CompanyUI(company);
                allCompanyList.add(c);
                allCompanyFilterNameList.add(c.toString());
            }
            LOGGER.debug("Loaded " + allCompanyList.size() + " records from company table");
        } catch (CalcInstrumentException ex) {
            LOGGER.warn(R.Errors.SQL_DATA_IS_UNAVAILEABLE);
        }
    }
    
     @FXML
    private void editCompany() {
        if (tableCompanyView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
         setDisabledCompanyFields(false);
         isUnderEditingProp.set(true);
        txfCompanyName.requestFocus();
    }

    @FXML
    private void addCompany() {    
            
        CompanyUI newRecordUI = new CompanyUI();      
        allCompanyList.add(newRecordUI);
        tableCompanyView.getSelectionModel().clearSelection();
        tableCompanyView.getSelectionModel().select(newRecordUI);
        tableCompanyView.fireEvent(EventFXUtil.getMouseClickEvent());
        editCompany();
    }

    @FXML
    private void saveCompany() {
        //setDisabledCompanyFields(true);
        btSaveCompany.setDisable(true);
        CompanyUI updCompany = tableCompanyView.getSelectionModel().getSelectedItem();
        Company company = null;
        try {
            updateCompanyFromFields(updCompany);
            company = companyConverter.convert(updCompany);
            if (company == null) {
                return;
            }
            if (company.getId() != 0) {
                companyService.update(company);
                LOGGER.debug("SAVE Company: company updated in DB");
            } else {
                int id = companyService.add(company);
                company.setId(id);
                LOGGER.debug("SAVE Company: company iserted in DB");
            }

        } catch (CalcInstrumentException ex) {
            LOGGER.warn(R.Errors.SQL_ERROR + " message: " + ex.getMessage());
            ControlFXUtils.showErrorDialog(R.Errors.SAVE_ITEM_ERROR+"\n"+
                    ex.getMessage());
        } finally {
            doAfterCompanyUpdate(company);
        }
    }
    
    private void doAfterCompanyUpdate(Company company){
        initCompanyTabPane();
            if(company == null)
                return;            
            CompanyUI currentCompanyUI = selectCompanyUIItemById(company.getId());
            if(currentCompanyUI != null)
                ControlFXUtils.selectTableItem(tableCompanyView, currentCompanyUI);
    }

    private void updateCompanyFromFields(CompanyUI companyUI)throws CalcInstrumentException {
        if (null == companyUI) {
            return;
        }
        checkCompanyFields(companyUI);
        companyUI.setName(txfCompanyName.getText().trim());
        companyUI.setHead(txfCompanyHead.getText().trim());
        companyUI.setAccountant(txfCompanyAccountant.getText().trim());
        
    }
    
    private void checkCompanyFields(CompanyUI companyUI)throws CalcInstrumentException{
        String name = txfCompanyName.getText().trim();
        if(name.isEmpty()){
            LOGGER.warn("Name of company is blank");
            throw new CalcInstrumentException("Назавание компании не должно быть пустым");
        }
        for(Company comp: companyService.getAll()){
            if (companyUI.getId()<=0 && name.equalsIgnoreCase(comp.getName())){
                LOGGER.warn("Name of company not unique");
                throw new CalcInstrumentException("Назавание компании должно быть уникальным");
            }
        }
        
    }

    private CompanyUI selectCompanyUIItemById(int id) {
        CompanyUI result = null;
        for (CompanyUI companyUI : tableCompanyView.getItems()) {
            if (id == companyUI.getId()) {
                return companyUI;
            }
        }
        return result;
    }

    @FXML
    private void deleteCompany() {
        CompanyUI companyUI = tableCompanyView.getSelectionModel().getSelectedItem();
        if (companyUI == null) {
            return;
        }
        try {
            Company company = companyConverter.convert(companyUI);
            if (company.getId()==0)
                return;
            
            Optional<ButtonType> choise = ControlFXUtils.getResponseDeleteDialog();
            if (choise.get() == ButtonType.OK) {
                LOGGER.debug("OK CHOISED!");
                companyService.delete(company);
                initCompanyTabPane();
                ControlFXUtils.selectFirstTableRecord(tableCompanyView);
                LOGGER.info("Company has been deleted");
            } 
        } catch (CalcInstrumentException ex) {
            LOGGER.warn("It is not possible delete company from DB message: "+ex.getMessage());
            ControlFXUtils.showErrorDialog(R.Errors.DELETE_ITEM_ERROR);
            initCompanyTabPane();
            ControlFXUtils.selectFirstTableRecord(tableCompanyView);
                
        }
    }


    // init Bank
    private void initBankTabPane() {
        initBankTableView();
        setDisabledBankFields(true);
        TextFieldValidateListener validateListener = new TextFieldValidateListener();
        txfBankName.textProperty().addListener(validateListener);
        btSaveBank.setDisable(true); 
        isUnderEditingProp.set(false);
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
        allBankFilterNameList.clear();
        allBankFilterNameList.add(R.ModelSettings.ALL_BANK_FILTER_NAME);
        try {
            for (Bank bank : bankService.getAll()) {
                BankUI c = new BankUI(bank);
                allBankList.add(c);
                allBankFilterNameList.add(c.toString());

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
         isUnderEditingProp.set(true);
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
            if (bank == null) {
                return;
            }
            if (bank.getId() != 0) {
                bankService.update(bank);
                LOGGER.debug("SAVE Bank: bank updated in DB");
            } else {
                int id = bankService.add(bank);
                bank.setId(id);
                LOGGER.debug("SAVE Bank: bank iserted in DB");
            }

        } catch (CalcInstrumentException ex) {
            LOGGER.warn(R.Errors.SQL_ERROR + " message: " + ex.getMessage());
        } finally {
            initBankTabPane();
            if (bank == null)
                return;
            BankUI selBank = selectBankUIItemById(bank.getId());
            if (selBank != null)
                ControlFXUtils.selectTableItem(tableBankView, selBank);
        }
    }

    private void updateBankFromFields(BankUI bankUI) {
        if (null == bankUI) {
            return;
        }
        bankUI.setName(txfBankName.getText().trim());
    }

    private BankUI selectBankUIItemById(int id) {
        BankUI result = null;
        for (BankUI bankUI : tableBankView.getItems()) {
            if (id == bankUI.getId()) {
                return bankUI;
            }
        }
        return result;
    }

    @FXML
    private void deleteBank() {
        BankUI bankUI = tableBankView.getSelectionModel().getSelectedItem();
        if (bankUI == null) {
            return;
        }
        try {
            Bank bank = bankConverter.convert(bankUI);
            if (bank.getId()==0)
                return;
            
            Optional<ButtonType> choise = ControlFXUtils.getResponseDeleteDialog();
            if (choise.get() == ButtonType.OK) {
                LOGGER.debug("OK CHOISED!");
                bankService.delete(bank);
                initBankTabPane();
                ControlFXUtils.selectFirstTableRecord(tableBankView);
                LOGGER.info("Bank was deleted");
            } 
        } catch (CalcInstrumentException ex) {
            LOGGER.warn("It is not possible delete Bank from DB message: "+ex.getMessage());
            ControlFXUtils.showErrorDialog(R.Errors.DELETE_ITEM_ERROR);
            initBankTabPane();
            ControlFXUtils.selectFirstTableRecord(tableBankView);
                
        }
    }

    // init Currency 
    private void initCurrencyTabPane() {
        initCurrencyTableView();
        setDisabledCurrencyFields(true);
        TextFieldValidateListener validateListener = new TextFieldValidateListener();
        txfCurrencyCode.textProperty().addListener(validateListener);
        txfCurrencyName.textProperty().addListener(validateListener);
        btSaveCurrency.setDisable(true);
        isUnderEditingProp.set(false);
       
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
        allCurrencyFilterNameList.clear();
        allCurrencyFilterNameList.add(R.ModelSettings.ALL_CURRENCY_FILTER_NAME);
        try {
            for (Currency curr : currencyService.getAll()) {
                CurrencyUI c = new CurrencyUI(curr);
                allCurrencyList.add(c);
                allCurrencyFilterNameList.add(c.toString());

            }
            LOGGER.debug("Loaded " + allCurrencyList.size() + " records from currency table");
        } catch (CalcInstrumentException ex) {
            LOGGER.warn(R.Errors.SQL_DATA_IS_UNAVAILEABLE);
        }
    }
    
     @FXML
    private void editCurrency() {
        if (tableCurrencyView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        setDisabledCurrencyFields(false);
         isUnderEditingProp.set(true);
        txfCurrencyName.requestFocus();
    }

    @FXML
    private void addCurrency() {    
            
        CurrencyUI newRecordUI = new CurrencyUI();       
        allCurrencyList.add(newRecordUI);
        tableCurrencyView.getSelectionModel().clearSelection();
        tableCurrencyView.getSelectionModel().select(newRecordUI);
        tableCurrencyView.fireEvent(EventFXUtil.getMouseClickEvent());
        editCurrency();
    }

    @FXML
    private void saveCurrency() {
        //setDisabledCurrencyFields(true);
        btSaveCurrency.setDisable(true);
        CurrencyUI updCurrency = tableCurrencyView.getSelectionModel().getSelectedItem();
        Currency currency = null;
        try {
            updateCurrencyFromFields(updCurrency);
            currency = currencyConverter.convert(updCurrency);
            if (currency == null) {
                return;
            }
            if (currency.getId() != 0) {
                currencyService.update(currency);
                LOGGER.debug("SAVE Currency: Currency updated in DB");
            } else {
                int id = currencyService.add(currency);
                currency.setId(id);
                LOGGER.debug("SAVE Currency: Currency iserted in DB");
            }

        } catch (CalcInstrumentException ex) {
            LOGGER.warn(R.Errors.SQL_ERROR + " message: " + ex.getMessage());
            ControlFXUtils.showErrorDialog(R.Errors.SAVE_ITEM_ERROR+"\n"+
                    ex.getMessage());
        } finally {
            doAfterCurrencyUpdate(currency);
        }
    }
    
    private void doAfterCurrencyUpdate(Currency currency){
        initCurrencyTabPane();
            if(currency == null)
                return;            
            CurrencyUI currentCurrencyUI = selectCurrencyUIItemById(currency.getId());
            if(currentCurrencyUI != null)
                ControlFXUtils.selectTableItem(tableCurrencyView, currentCurrencyUI);
    }

    private void updateCurrencyFromFields(CurrencyUI currencyUI)throws CalcInstrumentException {
        if (null == currencyUI) {
            return;
        }
        checkCurrencyFields(currencyUI);
        currencyUI.setName(txfCurrencyName.getText().trim());
        currencyUI.setCode(txfCurrencyCode.getText().trim());
        
    }
    
    private void checkCurrencyFields(CurrencyUI currencyUI)throws CalcInstrumentException{
        String name = txfCurrencyName.getText().trim();
        if(name.isEmpty()){
            LOGGER.warn("Name of currency is blank");
            throw new CalcInstrumentException("Назавание валюты не должно быть пустым");
        }
        for(Currency сurrency: currencyService.getAll()){
            if (currencyUI.getId()<=0 && name.equalsIgnoreCase(сurrency.getName())){
                LOGGER.warn("Name of сurrency not unique");
                throw new CalcInstrumentException("Назавание валюты должно быть уникальным");
            }
        }
        
    }

    private CurrencyUI selectCurrencyUIItemById(int id) {
        CurrencyUI result = null;
        for (CurrencyUI currencyUI : tableCurrencyView.getItems()) {
            if (id == currencyUI.getId()) {
                return currencyUI;
            }
        }
        return result;
    }

    @FXML
    private void deleteCurrency() {
        CurrencyUI currencyUI = tableCurrencyView.getSelectionModel().getSelectedItem();
        if (currencyUI == null) {
            return;
        }
        try {
            Currency currency = currencyConverter.convert(currencyUI);
            if (currency.getId()==0)
                return;
            
            Optional<ButtonType> choise = ControlFXUtils.getResponseDeleteDialog();
            if (choise.get() == ButtonType.OK) {
                LOGGER.debug("OK CHOISED!");
                currencyService.delete(currency);
                initCurrencyTabPane();
                ControlFXUtils.selectFirstTableRecord(tableCurrencyView);
                LOGGER.info("Currency has been deleted");
            } 
        } catch (CalcInstrumentException ex) {
            LOGGER.warn("It is not possible delete currency from DB message: "+ex.getMessage());
            ControlFXUtils.showErrorDialog(R.Errors.DELETE_ITEM_ERROR);
            initCurrencyTabPane();
            ControlFXUtils.selectFirstTableRecord(tableCurrencyView);
                
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
                        doSelectCompany();
                        return;
                    }
                    if (source.equals(tableCurrencyView)) {
                        doSelectCurrency();
                        return;
                    }
                    if (source.equals(tableLoanView)) {
                        doSelectLoan();                        
                    }

                }

            }
        }

        private void doSelectBank() {
            BankUI selectedBank = tableBankView.getSelectionModel().getSelectedItem();
            if (selectedBank == null) {
                return;
            }
            setDisabledBankFields(true);
            txfBankName.setText(selectedBank.getName());
            btSaveBank.setDisable(true);           
        }
                
        private void doSelectLoan() {
            LoanUI selectedLoan = tableLoanView.getSelectionModel().getSelectedItem();
            if (selectedLoan == null) {
                return;
            }
            setDisabledLoanFields(true);
            initCalcTab(selectedLoan);
            cbLoanCompany.setValue(selectedLoan.getCompany());
            cbLoanBank.setValue(selectedLoan.getBank());
            cbLoanCurrency.setValue(selectedLoan.getCurrency());
            txfLoanAmount.setText(selectedLoan.getAmount().toPlainString());
            txfLoanRate.setText(selectedLoan.getRate().toPlainString());
            dpLoanStartDate.setValue(DateUtil.getLocalDate(selectedLoan.getStartDate()));
            dpLoanEndDate.setValue(DateUtil.getLocalDate(selectedLoan.getEndDate()));
            cbLoanType.setValue(selectedLoan.getType());
            btSaveLoan.setDisable(true);            
        }
        
        private void doSelectCompany() {
            CompanyUI selectedCompany = tableCompanyView.getSelectionModel().getSelectedItem();
            if (selectedCompany == null) {
                return;
            }
            setDisabledCompanyFields(true);
            txfCompanyName.setText(selectedCompany.getName());
            txfCompanyHead.setText(selectedCompany.getHead());
            txfCompanyAccountant.setText(selectedCompany.getAccountant());
            btSaveCompany.setDisable(true);           
        }
        
         private void doSelectCurrency() {
            CurrencyUI selectedCurrency = tableCurrencyView.getSelectionModel().getSelectedItem();
            if (selectedCurrency == null) {
                return;
            }
            setDisabledCurrencyFields(true);
            txfCurrencyName.setText(selectedCurrency.getName());
            txfCurrencyCode.setText(selectedCurrency.getCode());
            btSaveCurrency.setDisable(true);            
        }
    }

    private class TextFieldValidateListener implements InvalidationListener {

        @Override
        public void invalidated(Observable observable) {          
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
                if (txfCurrencyName.isEditable()) {
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
    
    private class FilterLoanValidateListener implements InvalidationListener{

        @Override
        public void invalidated(Observable observable) {
            String companyName = cbLoanCompanyFilter.getValue();
            String bankName = cbLoanBankFilter.getValue();
            String currencyName = cbLoanCurrencyFilter.getValue();
            if (null == companyName)
                companyName = R.ModelSettings.ALL_COMPANY_FILTER_NAME;
            if (null == bankName) 
                bankName = R.ModelSettings.ALL_BANK_FILTER_NAME;
            if (null == currencyName) {
                currencyName = R.ModelSettings.ALL_CURRENCY_FILTER_NAME;
            }
            initAllLoanList();
            ObservableList<LoanUI> filteredByCompanyList = FXCollections.observableArrayList(allLoanList);
            
            if(!companyName.equals(R.ModelSettings.ALL_COMPANY_FILTER_NAME)){
                for(LoanUI loanUI : allLoanList){
                    if(companyName.equals(loanUI.getCompany().toString()))
                        continue;
                    else
                        filteredByCompanyList.remove(loanUI);                        
                }
            }
            
            ObservableList<LoanUI> filteredByBankList = FXCollections.observableArrayList(filteredByCompanyList);
            if(!bankName.equals(R.ModelSettings.ALL_BANK_FILTER_NAME)){
                for(LoanUI loanUI : filteredByCompanyList){
                    if(bankName.equals(loanUI.getBank().toString()))
                        continue;
                    else
                        filteredByBankList.remove(loanUI);                        
                }
            }
            
            ObservableList<LoanUI> filteredByCurrencyList = FXCollections.observableArrayList(filteredByBankList);
            if(!currencyName.equals(R.ModelSettings.ALL_CURRENCY_FILTER_NAME)){
                for(LoanUI loanUI : filteredByBankList){
                    if(currencyName.equals(loanUI.getCurrency().toString()))
                        continue;
                    else
                        filteredByCurrencyList.remove(loanUI);                        
                }
            }
            
            allLoanList.clear();
            allLoanList.addAll(filteredByCurrencyList);
            
        }
        
        
        
    }
    
    private class UnderEditingValidateListaner implements InvalidationListener{

        @Override
        public void invalidated(Observable observable) {
            if(!(observable instanceof SimpleBooleanProperty))
                return;
            SimpleBooleanProperty src = (SimpleBooleanProperty)observable;
            Tab selectedTab = tabPaneMain.getSelectionModel().getSelectedItem();
            
            if(selectedTab.equals(tabLoans)){
                btAddLoan.setDisable(src.getValue());
                tableLoanView.setDisable(src.getValue());
                return;
            }
            if(selectedTab.equals(tabBanks)){
                btAddBank.setDisable(src.getValue());
                tableBankView.setDisable(src.getValue());
                return;
            }
            if(selectedTab.equals(tabCompanies)){
                btAddCompany.setDisable(src.getValue());
                tableCompanyView.setDisable(src.getValue());
                return;
            }
            if(selectedTab.equals(tabCurrensies)){
                btAddCurrency.setDisable(src.getValue());
                tableCurrencyView.setDisable(src.getValue());
                return;
            }
            
        }
        
    }

}
