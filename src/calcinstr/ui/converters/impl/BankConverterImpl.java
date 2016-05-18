/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcinstr.ui.converters.impl;

import calcinstr.exceptions.CalcInstrumentException;
import calcinstr.factories.ServiceFactory;
import calcinstr.models.Bank;
import calcinstr.services.BankService;
import calcinstr.ui.converters.BankConverter;
import calcinstr.ui.entities.BankUI;

/**
 *
 * @author Victor
 */
public class BankConverterImpl implements BankConverter{
    
    private  BankService bankService = ServiceFactory.getBankService();            
    
    @Override
    public Bank convert(BankUI uiModel)throws CalcInstrumentException{
        if(uiModel == null)
            return null;
        Bank bank = bankService.get(uiModel.getId());
        if (bank != null){
            updateModelFromUIModel(bank, uiModel);           
        }else{
            bank = new Bank();
            updateModelFromUIModel(bank, uiModel);            
        }
        return bank;
    }
    
    private void updateModelFromUIModel(Bank model, BankUI uiModel){
        model.setName(uiModel.getName());
    }
    
}
