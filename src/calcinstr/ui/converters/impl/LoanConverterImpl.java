/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.converters.impl;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.factories.ConvertersFactory;
import calcinstr.factories.ServiceFactory;
import calcinstr.models.Company;
import calcinstr.models.Loan;
import calcinstr.services.LoanService;
import calcinstr.ui.converters.BankConverter;
import calcinstr.ui.converters.CompanyConverter;
import calcinstr.ui.converters.CurrencyConverter;
import calcinstr.ui.converters.LoanConverter;
import calcinstr.ui.entities.LoanUI;


public class LoanConverterImpl implements LoanConverter {

    private LoanService loanService = ServiceFactory.getLoanService();
    private BankConverter bankConverter = ConvertersFactory.getBankConverter();
    private CompanyConverter companyConverter  = ConvertersFactory.getCompanyConverter();
    private CurrencyConverter currencyConverter = ConvertersFactory.getCurrencyConverter();
    
    @Override
    public Loan convert(LoanUI uiModel)throws CalcInstrumentException{
         if(uiModel == null)
            return null;
        Loan loan = loanService.get(uiModel.getId());
        if (loan != null){
            updateModelFromUIModel(loan, uiModel);           
        }else{
            loan = new Loan();
            updateModelFromUIModel(loan, uiModel);            
        }
        return loan;
    }
    
    private void updateModelFromUIModel(Loan model, LoanUI uiModel)throws CalcInstrumentException{
        model.setAmount(uiModel.getAmount());
        model.setRate(uiModel.getRate());
        model.setType(uiModel.getType());
        model.setStartDate(uiModel.getStartDate());
        model.setEndDate(uiModel.getEndDate());
        model.setBank(bankConverter.convert(uiModel.getBank()));
        model.setCompany(companyConverter.convert(uiModel.getCompany()));
        model.setCurrency(currencyConverter.convert(uiModel.getCurrency()));
    }
}
