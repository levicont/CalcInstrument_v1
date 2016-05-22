/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calclinstr.calctest;

import calcinstr.config.R;
import calcinstr.models.Bank;
import calcinstr.models.Company;
import calcinstr.models.Currency;
import calcinstr.models.Loan;
import calcinstr.util.DateUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

/**
 *
 * @author victor
 */
public class GenericLoanCalculatorTest {
    
    protected Loan loan;
    protected Bank bank;
    protected Company company;
    protected Currency currency;
       
    
    protected void initLoan(){
        bank = new Bank();
        bank.setId(1);
        bank.setName("Union");
        
        company = new Company();
        company.setId(1);
        company.setName("MSI");
        company.setHead("Head");
        company.setAccountant("Accountant");
        
        currency = new Currency();
        currency.setId(1);
        currency.setCode("UAH");
        currency.setName("Hryivna UA");
        
        loan = new Loan();
        loan.setId(1);
        loan.setCompany(company);
        loan.setBank(bank);
        loan.setCurrency(currency);
        loan.setAmount(BigDecimal.valueOf(1000));
        loan.setRate(BigDecimal.valueOf(10));
        Date startDate = DateUtil.getDate(LocalDate.of(2016, Month.JANUARY, 25));
        Date endDate = DateUtil.getDate(LocalDate.of(2016, Month.JULY, 25));
        loan.setStartDate(startDate);
        loan.setEndDate(endDate);
        loan.setType(R.ModelSettings.LOAN_TYPES.get(R.ModelSettings.MOUNTH_LOAN_TYPE_KEY));
    }
}
