/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.entities;

import calcinstr.models.Loan;
import calcinstr.util.DateUtil;
import java.math.BigDecimal;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Victor
 */
public class LoanUI {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleObjectProperty<BigDecimal> amount = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<BigDecimal> rate = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Date> startDate = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Date> endDate = new SimpleObjectProperty<>();
    private final SimpleStringProperty type = new SimpleStringProperty();
    
    private final SimpleObjectProperty<BankUI> bank = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<CompanyUI> company = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<CurrencyUI> currency = new SimpleObjectProperty<>();
    
    private final SimpleStringProperty startDateFormat = new SimpleStringProperty();
    private final SimpleStringProperty endDateFormat = new SimpleStringProperty();
    
    public LoanUI(Loan loan){
        id.set(loan.getId());
        amount.set(loan.getAmount());
        rate.set(loan.getRate());
        startDate.set(loan.getStartDate());
        startDateFormat.set(DateUtil.format(startDate.get()));
        endDate.set(loan.getEndDate());
        endDateFormat.set(DateUtil.format(endDate.get()));
        type.set(loan.getType());
        
        bank.set(new BankUI(loan.getBank()));
        company.set(new CompanyUI(loan.getCompany()));
        currency.set(new CurrencyUI(loan.getCurrency()));
        
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleObjectProperty<BigDecimal> amountProperty() {
        return amount;
    }

    public SimpleObjectProperty<BigDecimal> rateProperty() {
        return rate;
    }

    public SimpleObjectProperty<Date> startDateProperty() {
        return startDate;
    }

    public SimpleObjectProperty<Date> endDateProperty() {
        return endDate;
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public SimpleObjectProperty<BankUI> bankProperty() {
        return bank;
    }

    public SimpleObjectProperty<CompanyUI> companyProperty() {
        return company;
    }

    public SimpleObjectProperty<CurrencyUI> currencyProperty() {
        return currency;
    }

    public SimpleStringProperty startDateFormatProperty() {
        return startDateFormat;
    }

    public SimpleStringProperty endDateFormatProperty() {
        return endDateFormat;
    }
    
    //Getters and Setters
    
    public int getId(){
        return id.get();
    }
    
    public void setId(int id){
        this.id.set(id);
    }
    
    public BigDecimal getAmount(){
        return amount.get();
    }
    
    public void setAmount(BigDecimal amount){
        this.amount.set(amount);                
    }
    
    public BigDecimal getRate(){
        return rate.get();
    }
    
    public void setRate(BigDecimal rate){
        this.rate.set(rate);                
    }
    
    public String getType(){
        return type.get();
    }
    
    public void setType(String type){
        this.type.set(type);
    }
    
    public Date getStartDate(){
        return startDate.get();
    }
    
    public void setStartDate(Date startDate){
        this.startDate.set(startDate);
    }
    
    public String getStartDateFormat(){
        return DateUtil.format(startDate.get());
    }
    
    public Date getEndDate(){
        return endDate.get();
    }
    
    public void setEndDate(Date endDate){
        this.endDate.set(endDate);
    }
    
    public String getEndDateFormat(){
        return DateUtil.format(endDate.get());
    }
    
    public BankUI getBank(){
        return bank.get();
        
    }
    
    public void setBank(BankUI bank){
        this.bank.set(bank);
    }
    
    public CompanyUI getCompany(){
        return company.get();
    }
    
    public void setCompany(CompanyUI company){
        this.company.set(company);
    }
    
    public CurrencyUI getCurrency(){
        return currency.get();
    }
    
    public void setCurrency(CurrencyUI currency){
        this.currency.set(currency);
    }

    @Override
    public String toString() {
        return "LoanUI{" + "amount=" + amount.get() + ", rate=" + rate.get() + ", startDate=" + startDate.get() + ", endDate=" + endDate.get() + ", type=" + type + '}';
    }
    
    
}
