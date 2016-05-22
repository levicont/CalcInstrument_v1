/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calclinstr.servicestest;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.factories.ServiceFactory;
import calcinstr.models.Bank;
import calcinstr.models.Company;
import calcinstr.models.Currency;
import calcinstr.models.Loan;
import calcinstr.services.BankService;
import calcinstr.services.CompanyService;
import calcinstr.services.CurrencyService;
import calcinstr.services.LoanService;
import calcinstr.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor
 */
public class LoanServiceTest {
    
    private static BankService bankService = ServiceFactory.getBankService();
    private static CompanyService companyService = ServiceFactory.getCompanyService();
    private static CurrencyService currencyService = ServiceFactory.getCurrencyService();
    private static LoanService loanService = ServiceFactory.getLoanService();
    
    private static Bank bank = new Bank();
    private static Company company = new Company();
    private static Currency currency = new Currency();
    
   
    
    public LoanServiceTest()throws Exception {
         bank = new Bank();
         bank.setName("TST");
         int id = bankService.add(bank);
         bank.setId(id);
 
         company = new Company();
         company.setName("TST");
         company.setHead("Ivanov");
         company.setAccountant("Petrov");
         id = companyService.add(company);
         company.setId(id);
      
         
         currency = new Currency();
         currency.setCode("TST");
         currency.setName("TST Money");
         id = currencyService.add(currency);
         currency.setId(id);
         
         
        
    }
    
    @Test
    public void addTest()throws CalcInstrumentException{
        Loan loan = new Loan();
         loan.setBank(bank);
         loan.setCompany(company);
         loan.setCurrency(currency);
         loan.setRate(BigDecimal.valueOf(2.0));
         loan.setAmount(BigDecimal.valueOf(10000.56));
         loan.setType("t");
         loan.setStartDate(new java.util.Date());
         LocalDate endDate = DateUtil.getLocalDate(new java.util.Date()).plusMonths(6);
         loan.setEndDate(DateUtil.getDate(endDate));
         int id = loanService.add(loan);
         assertNotEquals(-1, id);
         loan.setId(id);
         loanService.delete(loan);
         loan = loanService.get(id);
         assertNull(loan);
    }
    
    @AfterClass
    public static void afterClass(){
        try{
            bankService.delete(bank);            
        }catch(CalcInstrumentException ex){
            
        }
        
        try{
            companyService.delete(company);            
        }catch(CalcInstrumentException ex){
            
        }
        
        try{
            currencyService.delete(currency);            
        }catch(CalcInstrumentException ex){
            
        }
    }
    
    
    
}
