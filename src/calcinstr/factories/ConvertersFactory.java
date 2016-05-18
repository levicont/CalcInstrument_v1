/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.factories;

import calcinstr.ui.converters.BankConverter;
import calcinstr.ui.converters.CompanyConverter;
import calcinstr.ui.converters.CurrencyConverter;
import calcinstr.ui.converters.LoanConverter;
import calcinstr.ui.converters.impl.BankConverterImpl;
import calcinstr.ui.converters.impl.CompanyConverterImpl;
import calcinstr.ui.converters.impl.CurrencyConverterImpl;
import calcinstr.ui.converters.impl.LoanConverterImpl;


public class ConvertersFactory {
    
    private static  BankConverter bankConverter = new BankConverterImpl();
    private static  CompanyConverter companyConverter = new CompanyConverterImpl();
    private static  CurrencyConverter currencyConverter = new CurrencyConverterImpl();
    private static  LoanConverter loanConverter = new LoanConverterImpl();
    
    
    private ConvertersFactory(){}

    public static BankConverter getBankConverter() {
        return bankConverter;
    }

    public static CompanyConverter getCompanyConverter() {
        return companyConverter;
    }

    public static CurrencyConverter getCurrencyConverter() {
        return currencyConverter;
    }

    public static LoanConverter getLoanConverter() {
        return loanConverter;
    }
    
    
}
